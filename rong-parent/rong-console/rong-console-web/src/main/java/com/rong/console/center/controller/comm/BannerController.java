package com.rong.console.center.controller.comm;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbBanner;
import com.rong.sys.module.query.TsBanner;
import com.rong.sys.module.request.TqBanner;
import com.rong.sys.module.view.TvBanner;
import com.rong.sys.service.BannerService;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping(RightConst.COMM_BANNER_PATH)
public class BannerController extends BaseController<TbBanner, TqBanner, TvBanner, BannerService> {

    @Autowired
    public BannerController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_BANNER_PATH)
                .build(), TsBanner.Fields.id);
    }

    @Override
    public void otherEventForAddInit(HttpServletRequest request, HttpServletResponse response, TvBanner be) throws Exception {
        super.otherEventForAddInit(request, response, be);
        be.setState(CommonEnumContainer.State.NORMAL.getValue());
    }

    @Override
    @PostMapping(value="add")
    @ResponseBody
    public Result add_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqBanner req)throws Exception{
        preInsert(request,response,req);
        UserStorage adminStorage = admAspect.getAdminByServerStorage(request);
        req.getEntity().setCreateBy(adminStorage.getId());
        return Result.success(service.insert(req),"操作成功");
    }

    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvBanner view) throws Exception {
        super.otherEventForEditInit(request, response, view);
        view.setPics(service.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsBanner.Fields.pageType,view.getPageType())
                        .eq(TsBanner.Fields.deltag,false)
        ));
    }

    @Override
    public Result setToRecycleOrNormal(HttpServletRequest request, HttpServletResponse response, Long[] ids, Boolean deltag) throws Exception {

        if(CommonUtil.isEmpty(ids) || CommonUtil.isNull(deltag)){
            throw new CustomerException("参数有误，请检查排序项和ID项是否正确！", CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE);
        }
        TbBanner banner = service.selectItemByPrimaryKey(IdWrapper.valueOf(ids[0]));

        UpdateWrapper wrapper = new UpdateWrapper()
                .update(Elements.valueOf(TsBanner.Fields.deltag,deltag),
                        Elements.valueOf(TsBanner.Fields.updateDate,new Date())
                );
        wrapper.eq(TsBanner.Fields.pageType, banner.getPageType());
        service.updateSelectItem(wrapper);
        return Result.success();
    }

    @Override
    public Result<TvPageList<TvBanner>> dataGrid(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo) throws Exception {

        MultiTableQueryWrapper queryWrapper = getMultiQueryInfo(request,queryInfo);
        //wrapper queryInfo
        wrapQuery(queryWrapper,request);
        queryWrapper.selectAllFiels(false)
                .select("e.pageType")
                .select0(
                        SelectAlias.valueOf("max(e.id) id",true)
                        ,
                        SelectAlias.valueOf("max(e.create_by) createBy",true)
                        ,
                        SelectAlias.valueOf("max(e.create_date) createDate",true)
                        ,
                        SelectAlias.valueOf("max(u.real_name) createName",true)
                        ,
                        SelectAlias.valueOf("max(e.state) state",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbAdmUser.class,"u"), u->u
                    .eqc(CompareAlias.valueOf("u.id"),CompareAlias.valueOf("e.createBy"))
                )
        ;
        queryWrapper.groupBy(SelectAlias.valueOf("e.pageType"));
        return Result.success(service.selectPageList(queryWrapper),"successfull");
    }
}

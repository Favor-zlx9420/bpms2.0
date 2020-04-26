package com.rong.console.center.controller.fundmanage;

import com.rong.common.exception.DuplicateDataException;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.FundsBaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbPeopleManage;
import com.rong.fundmanage.module.query.TsPeopleManage;
import com.rong.fundmanage.module.request.TqPeopleManage;
import com.rong.fundmanage.service.PeopleManageService;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.query.TsMdPeople;
import com.rong.tong.pfunds.module.request.TqMdPeople;
import com.rong.tong.pfunds.module.view.TvMdPeople;
import com.rong.tong.pfunds.service.MdPeopleService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 机构管理
 */
@Controller
@RequestMapping(RightConst.PEOPLE_MANAGE_PATH)
public class PeopleManageController extends FundsBaseController<TbMdPeople, TqMdPeople, TvMdPeople, MdPeopleService> {
    private final PeopleManageService peopleManageService;
    private String bindUserAction;
    @Autowired
    public PeopleManageController(PeopleManageService peopleManageService) {
        super(new ViewModel.Builder()
                .basePath(RightConst.PEOPLE_MANAGE_PATH)
                .build(),
                TsMdPeople.Fields.personId
        );
        this.peopleManageService = peopleManageService;
        bindUserAction = viewModel.getBasePath() + "bind-user";
    }
    @GetMapping(value="bind-user")
    public String bindUser(HttpServletRequest request,HttpServletResponse response,Long personId)throws Exception{
        MultiTableQueryWrapper queryWrapper = service.getMultiCommonWrapper().eq(CompareAlias.valueOf("e.personId"),personId);
        wrapQuery(queryWrapper,request);
        TvMdPeople v = service.selectOneView(queryWrapper);
        request.setAttribute("entity", JSONUtil.toJSONString(v));
        viewModel.setAlterActionUrl(viewModel.getBasePath()+"edit");
        request.setAttribute("viewModel", this.viewModel);
        return viewModel.getBasePath()+"bind-user";
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("hasEditPermission",false);
        request.setAttribute("bindUserAction",bindUserAction);
    }
    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request){
        queryWrapper.selectAllFiels(true)
                .forceIndex("PERSON_ID")
                .select("m.visible,m.hotSearch,m.recommend,mb.userName userName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPeopleManage.class,"m"), x->
                        x.eqc(
                                CompareAlias.valueOf(TsPeopleManage.Fields.personId,"m"),
                                CompareAlias.valueOf(TsMdPeople.Fields.personId,ConstValue.MAIN_ALIAS)
                        )
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"), x->
                        x.eqc(
                                CompareAlias.valueOf(TsMemBase.Fields.id,"mb"),
                                CompareAlias.valueOf(TsPeopleManage.Fields.userId,"m")
                        )
                )
        ;
        String visibleSelector = request.getParameter("visibleSelector");
        if("true".equals(visibleSelector)){
            queryWrapper.eq(CompareAlias.valueOf("m.visible"),true);
        } else if ("false".equals(visibleSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf("m.visible"))
                    .eq(CompareAlias.valueOf("m.visible"),false)
            );
        }
        //
        String recommendSelector = request.getParameter("recommendSelector");
        if("true".equals(recommendSelector)){
            queryWrapper.eq(CompareAlias.valueOf("m.recommend"),true);
        } else if ("false".equals(recommendSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf("m.recommend"))
                    .eq(CompareAlias.valueOf("m.recommend"),false)
            );
        }
        String hotSearchSelector = request.getParameter("hotSearchSelector");
        if("true".equals(hotSearchSelector)){
            queryWrapper.eq(CompareAlias.valueOf("m.hotSearch"),true);
        } else if ("false".equals(hotSearchSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf("m.hotSearch"))
                    .eq(CompareAlias.valueOf("m.hotSearch"),false)
            );
        }
    }

    @Override
    @PostMapping(value = "edit")
    @ResponseBody
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqMdPeople req)throws Exception{
        Long personId = req.getEntity().getPersonId();
        Long bindUserId = req.getBindUserId();
        if(null == personId){
            throw new NoExistsException("人物id不能为空");
        }
        Boolean recommend = req.getRecommend();
        Boolean visible = req.getVisible();
        Boolean hotSearch = req.getHotSearch();
        //查询用户是否已经绑定其他基金经理了。
        if(bindUserId != null) {
            if (peopleManageService.selectCount(WrapperFactory.queryWrapper().eq(TsPeopleManage.Fields.userId, bindUserId)) > 0) {
                throw new DuplicateDataException("系统用户已经绑定过其他人，请选择其他系统用户");
            }
        }
        TbPeopleManage manager = peopleManageService.selectOne(
                WrapperFactory.queryWrapper().eq(TsPeopleManage.Fields.personId,personId)
        );
        //该用户没有绑定过
        if(manager == null){
            //add
            manager = new TbPeopleManage()
                    .setPersonId(personId)
                    .setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setVisible(visible)
                    .setUserId(bindUserId)
            ;
            peopleManageService.insert(new TqPeopleManage().setEntity(manager));
        }else{
            manager.setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setVisible(visible)
                    .setUserId(bindUserId)
            ;
            peopleManageService.updateByPrimary(new TqPeopleManage().setEntity(manager));
        }
        return Result.success(null,"更新成功！");
    }

}

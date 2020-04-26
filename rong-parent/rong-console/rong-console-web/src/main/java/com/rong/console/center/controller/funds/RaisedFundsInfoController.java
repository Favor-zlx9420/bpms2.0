package com.rong.console.center.controller.funds;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.console.center.controller.FundsBaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import com.rong.fundmanage.module.query.TsSecurityManage;
import com.rong.fundmanage.module.request.TqSecurityManage;
import com.rong.fundmanage.service.SecurityManageService;
import com.rong.tong.fund.module.entity.TbFund;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.fund.module.request.TqFundClass;
import com.rong.tong.fund.module.view.TvFundClass;
import com.rong.tong.fund.service.FundClassService;
import com.rong.tong.pfunds.module.query.TsPfund;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.RAISED_FUNDS_INFO_PATH)
public class RaisedFundsInfoController extends FundsBaseController<TbFundClass, TqFundClass, TvFundClass, FundClassService> {
    @Autowired
    private SecurityManageService securityManageService;
    @Autowired
    public RaisedFundsInfoController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.RAISED_FUNDS_INFO_PATH)
                .build(),
                TsPfund.Fields.id
        );
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("hasEditPermission",false);
    }
    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request){
        queryWrapper.selectAllFiels(true)
                .select("m.recommend,m.visible,m.hotSearch")
                .select("f.secFullName,f.secShortName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbFund.class,"f"), x->
                        x.eqc(
                                CompareAlias.valueOf("f.fundId"),
                                CompareAlias.valueOf("e.fundId")
                        )
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbSecurityManage.class,"m"), x->
                        x.eqc(CompareAlias.valueOf("m.securityId"),CompareAlias.valueOf("e.securityId"))
                        .eq(CompareAlias.valueOf(TsSecurityManage.Fields.type,"m"), CommonEnumContainer.OrgType.PUBLIC_PLACEMENT.getValue())
                        )
        ;
        String visibleSelector = request.getParameter("visibleSelector");
        if("true".equals(visibleSelector)){
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsSecurityManage.Fields.visible,"m"))
                    .eq(CompareAlias.valueOf(TsSecurityManage.Fields.visible,"m"),true)
                    );
        } else if ("false".equals(visibleSelector)) {
            queryWrapper.eq(CompareAlias.valueOf(TsSecurityManage.Fields.visible,"m"),false);
        }
        //
        String recommendSelector = request.getParameter("recommendSelector");
        if("true".equals(recommendSelector)){
            queryWrapper.eq(CompareAlias.valueOf(TsSecurityManage.Fields.recommend,"m"),true);
        } else if ("false".equals(recommendSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsSecurityManage.Fields.recommend,"m"))
                    .eq(CompareAlias.valueOf(TsSecurityManage.Fields.recommend,"m"),false)
            );
        }
        String hotSearchSelector = request.getParameter("hotSearchSelector");
        if("true".equals(hotSearchSelector)){
            queryWrapper.eq(CompareAlias.valueOf(TsSecurityManage.Fields.hotSearch,"m"),true);
        } else if ("false".equals(hotSearchSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsSecurityManage.Fields.hotSearch,"m"))
                    .eq(CompareAlias.valueOf(TsSecurityManage.Fields.hotSearch,"m"),false)
            );
        }
    }

    @Override
    @PostMapping(value = "edit")
    @ResponseBody
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqFundClass req)throws Exception{
        Long securityId = req.getEntity().getSecurityId().longValue();
        Boolean recommend = req.getRecommend();
        Boolean visible = req.getVisible();
        Boolean hotSearch = req.getHotSearch();
        TbSecurityManage manager = securityManageService.selectOne(
                new QueryWrapper()
                        .eq(TsSecurityManage.Fields.securityId,securityId)
        );
        if(manager == null){
            //add
            manager = new TbSecurityManage()
                    .setSecurityId(securityId)
                    .setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setType(CommonEnumContainer.OrgType.PUBLIC_PLACEMENT.getValue())
                    .setVisible(visible);
            securityManageService.insert(new TqSecurityManage().setEntity(manager));
        }else{
            //edit
            manager.setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setType(CommonEnumContainer.OrgType.PUBLIC_PLACEMENT.getValue())
                    .setVisible(visible);
            securityManageService.updateByPrimary(new TqSecurityManage().setEntity(manager));
        }
        return Result.success(null,"更新成功！");
    }
}

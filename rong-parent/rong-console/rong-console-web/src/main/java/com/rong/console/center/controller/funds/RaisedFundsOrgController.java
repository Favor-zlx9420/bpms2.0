package com.rong.console.center.controller.funds;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.console.center.controller.FundsBaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbOrgManage;
import com.rong.fundmanage.module.entity.TbOrgProxy;
import com.rong.fundmanage.module.query.TsOrgManage;
import com.rong.fundmanage.module.request.TqOrgManage;
import com.rong.fundmanage.service.OrgManageService;
import com.rong.member.module.entity.TbMemBase;
import com.rong.tong.fund.module.entity.TbFundInstInfo;
import com.rong.tong.fund.module.request.TqFundInstInfo;
import com.rong.tong.fund.module.view.TvFundInstInfo;
import com.rong.tong.fund.service.FundInstInfoService;
import com.rong.tong.pfunds.module.query.TsPfundInstInfo;
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
@RequestMapping(RightConst.RAISED_FUNDS_ORG_PATH)
public class RaisedFundsOrgController extends FundsBaseController<TbFundInstInfo, TqFundInstInfo, TvFundInstInfo, FundInstInfoService> {
    @Autowired
    private OrgManageService orgManageService;
    @Autowired
    public RaisedFundsOrgController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.RAISED_FUNDS_ORG_PATH)
                .build(),
                TsPfundInstInfo.Fields.id
        );
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("hasEditPermission",false);
    }
    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request){
        queryWrapper
                .selectAllFiels(true)
                .select("m.recommend,m.visible,m.hotSearch,mb.realName,mb.userName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbOrgManage.class,"m"), x->
                        x.eqc(
                                CompareAlias.valueOf("m.partyId"),
                                CompareAlias.valueOf("e.partyId")
                        )
                                .eq(CompareAlias.valueOf(TsOrgManage.Fields.type,"m"), CommonEnumContainer.OrgType.PUBLIC_PLACEMENT.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbOrgProxy.class,"op"), op->
                        op.eqc(CompareAlias.valueOf("op.partyId"),CompareAlias.valueOf("e.partyId"))
                        )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"), op->
                        op.eqc(CompareAlias.valueOf("mb.id"),CompareAlias.valueOf("op.userId"))
                )
        ;
        String visibleSelector = request.getParameter("visibleSelector");
        if("true".equals(visibleSelector)){
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsOrgManage.Fields.visible,"m"))
                    .eq(CompareAlias.valueOf(TsOrgManage.Fields.visible,"m"),true)
            );
        } else if ("false".equals(visibleSelector)) {
            queryWrapper.eq(CompareAlias.valueOf(TsOrgManage.Fields.visible,"m"),false);
        }
        //
        String recommendSelector = request.getParameter("recommendSelector");
        if("true".equals(recommendSelector)){
            queryWrapper.eq(CompareAlias.valueOf(TsOrgManage.Fields.recommend,"m"),true);
        } else if ("false".equals(recommendSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsOrgManage.Fields.recommend,"m"))
                    .eq(CompareAlias.valueOf(TsOrgManage.Fields.recommend,"m"),false)
            );
        }
        String hotSearchSelector = request.getParameter("hotSearchSelector");
        if("true".equals(hotSearchSelector)){
            queryWrapper.eq(CompareAlias.valueOf(TsOrgManage.Fields.hotSearch,"m"),true);
        } else if ("false".equals(hotSearchSelector)) {
            queryWrapper.or(x->x
                    .isNull(CompareAlias.valueOf(TsOrgManage.Fields.hotSearch,"m"))
                    .eq(CompareAlias.valueOf(TsOrgManage.Fields.hotSearch,"m"),false)
            );
        }
    }

    @Override
    @PostMapping(value = "edit")
    @ResponseBody
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqFundInstInfo req)throws Exception{
        Long partyId = req.getEntity().getPartyId().longValue();
        Boolean recommend = req.getRecommend();
        Boolean visible = req.getVisible();
        Boolean hotSearch = req.getHotSearch();
        TbOrgManage manager = orgManageService.selectOne(
                new QueryWrapper()
                        .eq(TsOrgManage.Fields.partyId,partyId)
        );
        if(manager == null){
            //add
            manager = new TbOrgManage()
                    .setPartyId(partyId)
                    .setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setType(CommonEnumContainer.OrgType.PUBLIC_PLACEMENT.getValue())
                    .setVisible(visible);
            TqOrgManage insertSelective = new TqOrgManage();
            insertSelective.setEntity(manager);
            orgManageService.insert(insertSelective);
        }else{
            //edit
            manager.setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setType(CommonEnumContainer.OrgType.PUBLIC_PLACEMENT.getValue())
                    .setVisible(visible);
            TqOrgManage updateItem = new TqOrgManage();
            updateItem.setEntity(manager);
            orgManageService.updateByPrimary(updateItem);
        }
        return Result.success(null,"更新成功！");
    }

}

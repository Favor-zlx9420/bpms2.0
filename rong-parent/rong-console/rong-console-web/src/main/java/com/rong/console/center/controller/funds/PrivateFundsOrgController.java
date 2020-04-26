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
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import com.rong.tong.pfunds.module.query.TsMdInstitution;
import com.rong.tong.pfunds.module.query.TsPfundInstInfo;
import com.rong.tong.pfunds.module.request.TqPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvPfundInstInfo;
import com.rong.tong.pfunds.service.PfundInstInfoService;
import com.vitily.mybatis.core.consts.ConstValue;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.PRIVATE_FUNDS_ORG_PATH)
public class PrivateFundsOrgController extends FundsBaseController<TbPfundInstInfo, TqPfundInstInfo, TvPfundInstInfo, PfundInstInfoService> {
    @Autowired
    private OrgManageService orgManageService;
    @Autowired
    public PrivateFundsOrgController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.PRIVATE_FUNDS_ORG_PATH)
                .build(),
                TsPfundInstInfo.Fields.id
        );
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("hasEditPermission",false);
    }
    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request){
        queryWrapper.selectAllFiels(false)
                .forceIndex("ID")
                .select("m.recommend,m.visible,m.hotSearch,mb.realName,mb.userName")
                .select0(
                        SelectAlias.valueOf(TsPfundInstInfo.Fields.id, ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsPfundInstInfo.Fields.partyId, ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMdInstitution.Fields.partyShortName,"i"),
                        SelectAlias.valueOf(TsMdInstitution.Fields.partyFullName,"i")
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"i"), x->
                        x.eqc(
                                CompareAlias.valueOf(TsMdInstitution.Fields.partyId,"i"),
                                CompareAlias.valueOf(TsPfundInstInfo.Fields.partyId,ConstValue.MAIN_ALIAS)
                        )
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbOrgManage.class,"m"), x->
                        x.eqc(
                                CompareAlias.valueOf(TsOrgManage.Fields.partyId,"m"),
                                CompareAlias.valueOf(TsPfundInstInfo.Fields.partyId,ConstValue.MAIN_ALIAS)
                        )
                                .eq(CompareAlias.valueOf(TsOrgManage.Fields.type,"m"), CommonEnumContainer.OrgType.PRIVATE_PLACEMENT.getValue())
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
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqPfundInstInfo req)throws Exception{
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
                    .setType(CommonEnumContainer.OrgType.PRIVATE_PLACEMENT.getValue())
                    .setVisible(visible);
            TqOrgManage insertSelective = new TqOrgManage();
            insertSelective.setEntity(manager);
            orgManageService.insert(insertSelective);
        }else{
            //edit
            manager.setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setType(CommonEnumContainer.OrgType.PRIVATE_PLACEMENT.getValue())
                    .setVisible(visible);
            TqOrgManage updateItem = new TqOrgManage();
            updateItem.setEntity(manager);
            orgManageService.updateByPrimary(updateItem);
        }
        return Result.success(null,"更新成功！");
    }
}

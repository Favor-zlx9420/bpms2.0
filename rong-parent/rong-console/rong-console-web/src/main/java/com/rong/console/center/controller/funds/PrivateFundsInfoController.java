package com.rong.console.center.controller.funds;

import com.alibaba.fastjson.JSON;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.FundsBaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import com.rong.fundmanage.module.query.TsProductLabel;
import com.rong.fundmanage.module.query.TsSecurityManage;
import com.rong.fundmanage.module.request.TqSecurityManage;
import com.rong.fundmanage.service.ProductLabelService;
import com.rong.fundmanage.service.SecurityManageService;
import com.rong.store.module.entity.TbDirectStoreOrg;
import com.rong.store.module.entity.TbDirectStoreProduct;
import com.rong.store.module.query.TsDirectStoreOrg;
import com.rong.store.module.query.TsDirectStoreProduct;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.service.LabelService;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.query.TsMdSecurity;
import com.rong.tong.pfunds.module.query.TsPfund;
import com.rong.tong.pfunds.module.request.TqPfund;
import com.rong.tong.pfunds.module.view.TvPfund;
import com.rong.tong.pfunds.service.PfundService;
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
@RequestMapping(RightConst.PRIVATE_FUNDS_INFO_PATH)
public class PrivateFundsInfoController extends FundsBaseController<TbPfund, TqPfund, TvPfund, PfundService> {
    @Autowired
    private LabelService labelService;
    @Autowired
    private ProductLabelService productLabelService;
    @Autowired
    private SecurityManageService securityManageService;
    @Autowired
    public PrivateFundsInfoController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.PRIVATE_FUNDS_INFO_PATH)
                .build(),
                TsPfund.Fields.id
        );
    }
    /**
     * 进入修改页面
     * @param request
     * @param response
     * @param securityId
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, long securityId)throws Exception{
        TvPfund entity = service.selectOneView(
                WrapperFactory.multiQueryWrapper()
                        .selectAllFiels(true)
                        .select("ms.secFullName")
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"ms"), ms->ms
                            .eqc(CompareAlias.valueOf("ms.securityId"),CompareAlias.valueOf("e.securityId"))
                        )
                        .eq(CompareAlias.valueOf("e.securityId"),securityId)
        );
        otherEventForEditInit(request,response,entity);
        request.setAttribute("entity", JSONUtil.toJSONString(entity));
        request.setAttribute("viewModel", this.viewModel);
        viewModel.setAlterActionUrl(viewModel.getBasePath()+"edit");
        return viewModel.getBasePath()+"alter";
    }


    @Override
    public void otherEventForEditInit(HttpServletRequest request, HttpServletResponse response, TvPfund view) throws Exception {
        super.otherEventForEditInit(request, response, view);
        view.setLabels(productLabelService.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsProductLabel.Fields.securityId, view.getSecurityId())
                        .eq(TsProductLabel.Fields.deltag, false)

        ));
        attributes(request);
    }
    private void attributes(HttpServletRequest request){
        request.setAttribute("labelList", JSON.toJSONString(labelService.selectList(
                WrapperFactory.queryWrapper()
                        .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.BRAND_LABEL.getValue())
                        .eq(TsLabel.Fields.deltag, false)
        )));
    }
    public void otherEventForListInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setAttribute("hasEditPermission",false);
    }
    @Override
    public void wrapQuery(MultiTableQueryWrapper queryWrapper, HttpServletRequest request){
        queryWrapper.selectAllFiels(false)
                .forceIndex("ID")
                .select0(
                        SelectAlias.valueOf(TsPfund.Fields.securityId, ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsPfund.Fields.id, ConstValue.MAIN_ALIAS),
                        SelectAlias.valueOf(TsMdSecurity.Fields.secShortName,"s"),
                        SelectAlias.valueOf(TsMdSecurity.Fields.secFullName,"s")
                )
                .select0(
                        SelectAlias.valueOf(TsSecurityManage.Fields.recommend,"m"),
                        SelectAlias.valueOf(TsSecurityManage.Fields.visible,"m"),
                        SelectAlias.valueOf(TsSecurityManage.Fields.hotSearch,"m")
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"s"), x->
                        x.eqc(
                                CompareAlias.valueOf(TsMdSecurity.Fields.securityId,"s"),
                                CompareAlias.valueOf(TsPfund.Fields.securityId,ConstValue.MAIN_ALIAS)
                        )
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbSecurityManage.class,"m"), x->
                        x.eqc(CompareAlias.valueOf(TsSecurityManage.Fields.securityId,"m"),
                                CompareAlias.valueOf(TsPfund.Fields.securityId,ConstValue.MAIN_ALIAS))
                        .eq(CompareAlias.valueOf(TsSecurityManage.Fields.type,"m"), CommonEnumContainer.OrgType.PRIVATE_PLACEMENT.getValue())
                        )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbDirectStoreOrg.class,"dso"), dso->
                        dso.eqc(CompareAlias.valueOf(TsDirectStoreOrg.Fields.partyId,"dso"),
                                CompareAlias.valueOf(TsPfund.Fields.investConsultant,"e"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbDirectStoreProduct.class,"dsp"), dsp->
                        dsp.eqc(CompareAlias.valueOf(TsDirectStoreProduct.Fields.securityId,"dsp"),
                                CompareAlias.valueOf(TsPfund.Fields.securityId,"e"))
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
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqPfund req)throws Exception{
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
                    .setType(CommonEnumContainer.OrgType.PRIVATE_PLACEMENT.getValue())
                    .setVisible(visible);
            TqSecurityManage tqSecurityManage = new TqSecurityManage();
            tqSecurityManage.setLabelIds(req.getLabelIds());
            tqSecurityManage.setEntity(manager);
            tqSecurityManage.setLabelReason(req.getLabelReason());
            tqSecurityManage.setLabelVar0(req.getLabelVar0());
            tqSecurityManage.setLabelVar1(req.getLabelVar1());
            securityManageService.insert(tqSecurityManage);
        }else{
            //edit
            manager.setRecommend(recommend)
                    .setHotSearch(hotSearch)
                    .setType(CommonEnumContainer.OrgType.PRIVATE_PLACEMENT.getValue())
                    .setVisible(visible);
            TqSecurityManage tqSecurityManage = new TqSecurityManage();
            tqSecurityManage.setLabelIds(req.getLabelIds());
            tqSecurityManage.setEntity(manager);
            tqSecurityManage.setLabelReason(req.getLabelReason());
            tqSecurityManage.setLabelVar0(req.getLabelVar0());
            tqSecurityManage.setLabelVar1(req.getLabelVar1());
            securityManageService.updateByPrimary(tqSecurityManage);
        }
        return Result.success(null,"更新成功！");
    }

}

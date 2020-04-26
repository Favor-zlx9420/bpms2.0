package com.rong.console.center.controller.funds;

import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import com.rong.fundmanage.module.query.TsSecurityManage;
import com.rong.fundmanage.module.request.TqSecurityManage;
import com.rong.fundmanage.module.view.TvSecurityManage;
import com.rong.fundmanage.service.SecurityManageService;
import com.rong.tong.fund.module.entity.TbFund;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.query.TsPfund;
import com.rong.tong.pfunds.service.PfundService;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(RightConst.SECURITY_MANAGE_PATH)
public class ProductInfoController extends BaseController<TbSecurityManage, TqSecurityManage, TvSecurityManage, SecurityManageService> {
    @Autowired
    private PfundService pfundService;
    @Autowired
    public ProductInfoController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.SECURITY_MANAGE_PATH)
                .build(),
                TsSecurityManage.Fields.id
        );
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, QueryInfo queryInfo) throws Exception {
        super.list(request, response, queryInfo);
        return "/fund-manage/security/list";
    }

    @Override
    protected MultiTableQueryWrapper multiTableQueryWrapper(HttpServletRequest request){
        String type = request.getParameter("type");
        return multiTableQueryWrapper(request,type);
    }

    protected MultiTableQueryWrapper multiTableQueryWrapper(HttpServletRequest request,String type){
        MultiTableQueryWrapper queryWrapper;
        if("0".equals(type)){
            queryWrapper = WrapperFactory.multiQueryWrapper(TbPfund.class,"e");
            queryWrapper
                    .select("e.manager managerName,e.duration,e.scaleInitial,e.applyFee,e.redeemFee")
                    .select0(
                            SelectAlias.valueOf("(select VALUE_NAME_CN from `tong-rong`.sys_code where CODE_TYPE_ID=40032 and VALUE_NUM_CD=e.INVEST_STRATEGY limit 1) investStrategy",true)

                    )
                    .forceIndex("ID")
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), mi->mi
                            .eqc(CompareAlias.valueOf("mi.partyId"),CompareAlias.valueOf("e.investConsultant"))
                    )
            ;
            String investStrategy = request.getParameter("investStrategy");
            if(StringUtil.isNotEmpty(investStrategy)){
                queryWrapper.eq(CompareAlias.valueOf("e.investStrategy"),Integer.valueOf(investStrategy));
            }
        }else{
            queryWrapper = WrapperFactory.multiQueryWrapper(TbFundClass.class,"e");
            queryWrapper.leftJoin(ClassAssociateTableInfo.valueOf(TbFund.class,"f"), f->f.eqc(CompareAlias.valueOf("f.fundId"),CompareAlias.valueOf("e.fundId")));
            queryWrapper
                    .select("e.className,e.expireDate")
                    .select0(
                            //基金经理
                            SelectAlias.valueOf("(select name from `tong-rong`.`md_people` mp,`tong-rong`.fund_manager_new fm where fm.SECURITY_ID=e.SECURITY_ID and mp.PERSON_ID = fm.PERSON_ID limit 1) managerName",true)
                            ,//投资策略
                            SelectAlias.valueOf("(select VALUE_NAME_CN from `tong-rong`.sys_code a,`tong-rong`.fund_type b where a.CODE_TYPE_ID=40123 and a.VALUE_NUM_CD = b.VALUE_NUM_CD and b.SECURITY_ID=e.security_id) investStrategy",true)

                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), mi->mi
                            .eqc(CompareAlias.valueOf("mi.partyId"),CompareAlias.valueOf("f.managementCompany"))
                    )
            ;
        }
        queryWrapper.leftJoin(ClassAssociateTableInfo.valueOf(TbSecurityManage.class,"sm"), sm->sm
                .eqc(CompareAlias.valueOf("sm.securityId"),CompareAlias.valueOf("e.securityId"))
        )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"md"), md->md
                        .eqc(CompareAlias.valueOf("md.securityId"),CompareAlias.valueOf("e.securityId"))
                )
        ;
        queryWrapper.select("e.securityId,md.secShortName,md.secFullName,e.establishDate,mi.partyShortName");
        queryWrapper.select0(
                SelectAlias.valueOf(type + " type",true)
                ,
                SelectAlias.valueOf("ifnull(sm.visible,true) visible",true)
        );
        String securityName = request.getParameter("securityName");
        if(StringUtil.isNotEmpty(securityName)){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("md.secShortName"),"%" + securityName + "%")
                    .like(CompareAlias.valueOf("md.secFullName"),"%" + securityName + "%")
            );
        }
        String partyName = request.getParameter("partyName");
        if(StringUtil.isNotEmpty(partyName)){
            queryWrapper.or(or->or
                    .like(CompareAlias.valueOf("mi.partyShortName"),"%" + partyName + "%")
                    .like(CompareAlias.valueOf("mi.partyFullName"),"%" + partyName + "%")
            );
        }
        String visible = request.getParameter("visible");
        if("false".equals(visible)){
            queryWrapper.eq(CompareAlias.valueOf("sm.visible"),false);
        }else if("true".equals(visible)){
            queryWrapper.or(or->or
                    .eq(CompareAlias.valueOf("sm.visible"),true)
                    .isNull(CompareAlias.valueOf("sm.visible"))
            );
        }
        return queryWrapper;
    }

    @Override
    public Result edit_post(HttpServletRequest request, HttpServletResponse response, @RequestBody TqSecurityManage req) throws Exception {

        //bean.setReqIp(HttpClientUtil.getIP(request));
        preUpdate(request,response,req);
        TbSecurityManage entity = req.getEntity();
        TbSecurityManage manage = service.selectOne(WrapperFactory.queryWrapper().eq(TsSecurityManage.Fields.securityId,req.getEntity().getSecurityId()
                ));
        if(null != manage){
            //update
            entity.setId(manage.getId());
            service.updateByPrimary(req);
            return Result.success();
        }else{
            return add_post(request,response,req);
        }
    }

    @Override
    public String view(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
        TbPfund pfund = pfundService.selectOne(WrapperFactory.queryWrapper().eq(TsPfund.Fields.securityId,id));
        String type = "0";
        if(null == pfund){
            type = "2";
        }
        TvSecurityManage entity = service.selectOneView(multiTableQueryWrapper(request,type).eq(CompareAlias.valueOf("e.securityId"),id));
        otherEventForViewInit(request,response,entity);
        request.setAttribute("entity", JSONUtil.toJSONString(entity));
        request.setAttribute("viewModel", this.viewModel);
        return viewModel.getViewAction();
    }
}

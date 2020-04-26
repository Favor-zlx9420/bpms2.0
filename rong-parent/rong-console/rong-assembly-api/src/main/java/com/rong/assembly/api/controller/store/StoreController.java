package com.rong.assembly.api.controller.store;

import com.rong.assembly.api.mapper.RespOrgInfoMapper;
import com.rong.assembly.api.mapper.RespPeopleInfoMapper;
import com.rong.assembly.api.mapper.RespTrFundsMapper;
import com.rong.assembly.api.module.request.TqListUser;
import com.rong.assembly.api.module.request.TqOrgInfo;
import com.rong.assembly.api.module.request.store.TqStoreDesign;
import com.rong.assembly.api.module.request.store.TqStoreOrgList;
import com.rong.assembly.api.module.request.store.TqStoreProduct;
import com.rong.assembly.api.module.request.store.TqUserServiceOrg;
import com.rong.assembly.api.module.response.org.TrRespOrg;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.assembly.api.module.response.product.TrFunds;
import com.rong.assembly.api.module.response.store.TrStoreDesign;
import com.rong.assembly.api.service.OrgService;
import com.rong.assembly.api.util.SelectAliasUtil;
import com.rong.assembly.api.util.SortUtil;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbPeopleManage;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentCompanyRanking;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentGr;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentIncome;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentPerf;
import com.rong.fundmanage.module.entity.TbProductLabel;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.util.MemberUtil;
import com.rong.store.module.entity.TbDirectStoreDesign;
import com.rong.store.module.entity.TbDirectStoreOrg;
import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import com.rong.store.module.entity.TbDirectStoreProduct;
import com.rong.store.module.entity.TbDirectStoreUser;
import com.rong.store.module.query.TsDirectStoreOrg;
import com.rong.store.module.query.TsDirectStoreUser;
import com.rong.store.module.view.TvDirectStoreUser;
import com.rong.store.service.DirectStoreOrgService;
import com.rong.store.service.DirectStoreUserService;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import com.rong.tong.pfunds.module.entity.TbPfundInstRep;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
import com.vitily.mybatis.core.entity.FieldField;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CollectionUtils;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "直营店")
@RestController
@RequestMapping("store")
public class StoreController {
    @Autowired
    private CommonServiceCache commonServiceCache;
    @Autowired
    private DirectStoreUserService directStoreUserService;
    @Autowired
    private DirectStoreOrgService directStoreOrgService;
    @Autowired
    private RespTrFundsMapper respTrFundsMapper;
    @Autowired
    private RespPeopleInfoMapper respPeopleInfoMapper;
    @Autowired
    private RespOrgInfoMapper respOrgInfoMapper;
    @Autowired
    private OrgService orgService;
    /**
     * 驻店大佬
     * @param req
     * @return
     */
    @ApiOperation(value = "驻店大佬")
    @GetMapping("user/recommendUser")
    public Result<TvPageList<TrManager>> recommendUser(TqListUser req){
        MultiTableQueryWrapper queryWrapper = directStoreUserService.getMultiCommonWrapper(TbDirectStoreUser.class)
                .select("e.userId,mb.realName,mb.nickName,mb.position,mb.headPortrait")
                .select0(
                        SelectAlias.valueOf("(select person_id from tb_people_manage where user_id=e.user_id limit 1) personId",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"), mb->mb.eqc(CompareAlias.valueOf("e.userId"),CompareAlias.valueOf("mb.id")))
                .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.StoreUserType.FUND_MANAGER.getValue())
                .eq(CompareAlias.valueOf("e.recommend"),true)
                .eq(CompareAlias.valueOf("e.visible"),true)
                .eq(CompareAlias.valueOf("e.deltag"),false)
                .page(req.getPageInfo())
                .orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf(TsDirectStoreUser.Fields.sort,"e")))
        ;
        TvPageList<TrManager> pageList = new TvPageList<>();
        req.getPageInfo().setRecordCount(respPeopleInfoMapper.selectMultiTableCount(queryWrapper));
        pageList.setPageInfo(req.getPageInfo());
        pageList.setList(respPeopleInfoMapper.selectViewList(queryWrapper));

        return Result.success(pageList);
    }

    /**
     * 用户服务的直营店机构（仅私募）
     * @param req
     * @return
     */
    @ApiOperation(value = "用户服务的直营店机构（仅私募）")
    @GetMapping("user/serviceOrg")
    public Result<TrRespOrg> recommendUser(TqUserServiceOrg req){
        Long partyId = null;
        //先查直营店用户
        TbDirectStoreUser user = directStoreUserService.selectOne(
          WrapperFactory.queryWrapper().select("partyId").eq(TsDirectStoreUser.Fields.userId,req.getServiceUserId())
        );
        if(user != null){
            partyId = user.getPartyId();
        }else{
            TbDirectStoreOrg org = directStoreOrgService.selectOne(
                    WrapperFactory.queryWrapper().select("partyId").eq(TsDirectStoreOrg.Fields.userId,req.getServiceUserId())
            );
            if(org != null){
                partyId = org.getPartyId();
            }
        }
        if(null == partyId){
            return Result.success();
        }
        TqOrgInfo tqOrgInfo = new TqOrgInfo();
        tqOrgInfo.setPartyId(partyId);
        if(null != req.getCustomerUserId()) {
            tqOrgInfo.setLoginUserId(req.getCustomerUserId());
        }
        return Result.success(orgService.selectPriOrgInfo(tqOrgInfo));
    }

    /**
     * 直营店列表
     * @param req
     * @return
     */
    @ApiOperation(value = "直营店列表")
    @GetMapping("org/list")
    public Result<TvPageList<TrRespOrg>> productList(TqStoreOrgList req){
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbDirectStoreOrg.class)
                .selectAllFiels(true)
                .select("e.partyId,mi.partyShortName,ccr.scale,ccr.mainFundType,fi.ideaStrategy,mi.officeAddr")
                .select0(SelectAliasUtil.getFavOfOrg(req.getLoginUserId()))
                .select0(
                        //近一年收益（仅私募）
                        SelectAlias.valueOf("case when e.type = 0 then (select avg(return_of_latest_year) from tb_private_funds_current_perf where party_id=e.party_id) else null end returnOfLatestYear",true)
                        //年化收益（仅私募）
                        ,SelectAlias.valueOf("case when e.type = 0 then (select avg(return_a) from tb_private_funds_current_income where party_id=e.party_id) else null end returnA",true)
                        //累计年化收益（仅私募）
                        ,SelectAlias.valueOf("case when e.type = 0 then (select avg(return_accum) from tb_private_funds_current_income where party_id=e.party_id) else null end returnA",true)
                        //近一年风险（仅私募）
                        ,SelectAlias.valueOf("case when e.type = 0 then (select avg(risk_of_latest_year) from tb_private_funds_current_perf where party_id=e.party_id) else null end riskOfLatestYear",true)
                        //代表产品投资策略，只有私募才有
                        ,SelectAlias.valueOf("(SELECT VALUE_NAME_CN FROM `tong-rong`.sys_code WHERE CODE_TYPE_ID =40032 and VALUE_NUM_CD=fpf.INVEST_STRATEGY) investStrategy",true)
                    )
                .select("fms.secFullName repSecurityFullName,fir.securityId repSecurityId")
                //代表产品成立以来收益、近一年收益
                .select("fcp.returnOfLatestYear repSecReturnOfLatestYear,fcp.returnOfEstablish repSecReturnOfEstablish")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"),
                        x->x.where(FieldField.fromCondition("mi.partyId.eq","e.partyId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPfundInstInfo.class,"fi"),
                        x->x.where(FieldField.fromCondition("fi.partyId.eq","e.partyId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPfundInstRep.class,"fir"), fir->
                        fir.eqc(CompareAlias.valueOf("fir.partyId"),CompareAlias.valueOf("e.partyId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"fms"), fms->
                        fms.eqc(CompareAlias.valueOf("fms.securityId"),CompareAlias.valueOf("fir.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class,"fpf"), fpf->
                        fpf.eqc(CompareAlias.valueOf("fpf.securityId"),CompareAlias.valueOf("fir.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentPerf.class,"fcp"), fcp->
                        fcp.eqc(CompareAlias.valueOf("fcp.securityId"),CompareAlias.valueOf("fir.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentCompanyRanking.class,"ccr"),
                        x->x.where(FieldField.fromCondition("ccr.partyId.eq","e.partyId"))
                )
                .eq(CompareAlias.valueOf("e.visible"),true)
                .eq(CompareAlias.valueOf("e.deltag"),false)
                .page(pageInfo)
                ;
        if(null != req.getType()){
            queryWrapper.eq(CompareAlias.valueOf("e.type"),req.getType());
        }
        if(null != req.getRecommend()){
            queryWrapper.eq(CompareAlias.valueOf("e.recommend"),req.getRecommend());
        }
        if(StringUtil.isNotEmpty(req.getPartyFullName())){
            queryWrapper.or(or->
                    or
                            .like(CompareAlias.valueOf("mi.partyShortName"),req.getPartyFullName())
                            .like(CompareAlias.valueOf("mi.partyFullName"),req.getPartyFullName())
                    );
        }
        if(!CollectionUtils.isEmpty(req.getLabelIds())){
            queryWrapper
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbDirectStoreOrgLabel.class,"dsol"), dsol->
                            dsol.where(FieldField.fromCondition("dsol.partyId.eq","e.partyId"))
                    )
                    .in(CompareAlias.valueOf("dsol.labelId"),req.getLabelIds());
        }

        if(StringUtil.isNotEmpty(req.getScale())){
            queryWrapper.eq(CompareAlias.valueOf("ccr.scale"),req.getScale());
        }
        if(StringUtil.isNotEmpty(req.getRegCity())){
            queryWrapper.eq(CompareAlias.valueOf("ccr.regCity"),req.getRegCity());
        }
        if(null != req.getBeginReturnRate1y()){
            queryWrapper.ge(CompareAlias.valueOf("ccr.returnRate1y"),req.getBeginReturnRate1y());
        }
        if(null != req.getEndReturnRate1y()){
            queryWrapper.le(CompareAlias.valueOf("ccr.returnRate1y"),req.getEndReturnRate1y());
        }

        if(null != req.getSearchInterval()){
            queryWrapper.orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("ccr."+orderRate[req.getSearchInterval()])));
        }
        SortUtil.setOrgSort(queryWrapper,pageInfo);
        TvPageList<TrRespOrg> pageList = new TvPageList<>();
        pageInfo.setRecordCount(respTrFundsMapper.selectMultiTableCount(queryWrapper));
        pageList.setPageInfo(pageInfo);
        pageList.setList(respOrgInfoMapper.selectViewList(queryWrapper));
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        TqOrgInfo tqOrgInfo = new TqOrgInfo();
        tqOrgInfo.setLoginUserId(req.getLoginUserId());
        for(TrRespOrg org:pageList.getList()){
            //驻店客服列表
            List<TvDirectStoreUser> storeUsers = directStoreUserService.selectViewList(directStoreUserService.getMultiCommonWrapper()
                    .eq(CompareAlias.valueOf(TsDirectStoreUser.Fields.partyId), org.getPartyId())
                    .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.StoreUserType.SERVICE.getValue())
                    .eq(CompareAlias.valueOf(TsDirectStoreUser.Fields.state, "e"), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())
                    .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
            );
            for(TvDirectStoreUser m:storeUsers){
                m.setOnlineState(memCache.existsInServer(m.getType()+"-" + m.getUserId())?
                        CommonEnumContainer.OnlineState.ON_LINE.getValue(): CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
            }
            org.setCustomerServers(storeUsers);
            //产品经理列表
            tqOrgInfo.setPartyId(org.getPartyId());
            org.setManagers(orgService.selectManagerOfPriOrg(tqOrgInfo));
        }
        return Result.success(pageList);
    }


    private String[] orderRate = {
            "",
            "returnRate1m",
            "returnRate3m",
            "returnRate6m",
            "returnRate1y",
            "returnRate2y",
            "returnRate3y",
            "returnRate5y",
            "returnRateEst",
            "returnRateYtd"
    };

    /**
     * 驻店产品
     * @param req
     * @return
     */
    @ApiOperation(value = "驻店产品")
    @GetMapping("product/list")
    public Result<TvPageList<TrFunds>> productList(TqStoreProduct req){
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbDirectStoreProduct.class)
                .selectAllFiels(true)
                .select0(SelectAliasUtil.getFavOfPro(req.getLoginUserId()))
                .select("e.securityId,md.secShortName,md.secFullName,pf.scaleInitial,pf.establishDate,pi.returnAccum,pi.returnA,pn.accumNav,pn.nav")
                .select("cp.returnOfLatestYear,cp.riskOfLatestYear,mi.partyId,mi.partyShortName")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentNav.class,"pn"),
                        x->x.where(FieldField.fromCondition("pn.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentIncome.class,"pi"),
                        x->x.where(FieldField.fromCondition("pi.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentPerf.class,"cp"),
                        x->x.where(FieldField.fromCondition("cp.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentGr.class,"gr"),
                        x->x.where(FieldField.fromCondition("gr.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"md"),
                        x->x.where(FieldField.fromCondition("md.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class,"pf"),
                        x->x.where(FieldField.fromCondition("pf.securityId.eq","e.securityId"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"),
                        x->x.where(FieldField.fromCondition("mi.partyId.eq","pf.investConsultant"))
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPrivateFundsCurrentCompanyRanking.class,"ccr"),
                        x->x.where(FieldField.fromCondition("ccr.partyId.eq","mi.partyId"))
                )
                .eq(CompareAlias.valueOf("e.visible"),true)
                .eq(CompareAlias.valueOf("e.deltag"),false)
                .page(pageInfo)
                ;
        if(null != req.getType()){
            queryWrapper.eq(CompareAlias.valueOf("e.type"),req.getType());
        }
        if(null != req.getRecommend()){
            queryWrapper.eq(CompareAlias.valueOf("e.recommend"),req.getRecommend());
        }
        if(StringUtil.isNotEmpty(req.getSecFullName())){
            queryWrapper.or(or->
                    or
                            .like(CompareAlias.valueOf("md.secShortName"),req.getSecFullName())
                            .like(CompareAlias.valueOf("md.secFullName"),req.getSecFullName())
            );
        }
        if(!CollectionUtils.isEmpty(req.getLabelIds())){
            queryWrapper
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbProductLabel.class,"pl"), dsol->
                            dsol.where(FieldField.fromCondition("pl.securityId.eq","e.securityId"))
                    )
                    .in(CompareAlias.valueOf("pl.labelId"),req.getLabelIds());
        }

        if(null != req.getRegCity()){
            queryWrapper.eq(CompareAlias.valueOf("mi.regCity"),req.getRegCity());
        }
        if(StringUtil.isNotEmpty(req.getScale())){
            queryWrapper.eq(CompareAlias.valueOf("ccr.scale"),req.getScale());
        }
        if(null != req.getInvestStrategy()){
            queryWrapper.in(CompareAlias.valueOf("pf.investStrategy"), StringUtil.StringsToLongList(req.getInvestStrategy().split(",")));
        }
        if(null != req.getSearchInterval()){
            queryWrapper.orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("gr."+orderRate[req.getSearchInterval()])));
        }
        if(null != req.getBeginReturnRate1y()){
            queryWrapper.ge(CompareAlias.valueOf("gr.returnRate1y"),req.getBeginReturnRate1y());
        }
        if(null != req.getEndReturnRate1y()){
            queryWrapper.le(CompareAlias.valueOf("gr.returnRate1y"),req.getEndReturnRate1y());
        }

        SortUtil.setOrgSort(queryWrapper,pageInfo);
        TvPageList<TrFunds> pageList = new TvPageList<>();
        pageInfo.setRecordCount(respTrFundsMapper.selectMultiTableCount(queryWrapper));
        pageList.setPageInfo(pageInfo);
        pageList.setList(respTrFundsMapper.selectViewList(queryWrapper));
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        for(TrFunds funds:pageList.getList()){
            //驻店客服列表
            List<TvDirectStoreUser> storeUsers = directStoreUserService.selectViewList(directStoreUserService.getMultiCommonWrapper()
                    .eq(CompareAlias.valueOf(TsDirectStoreUser.Fields.partyId), funds.getPartyId())
                    .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.StoreUserType.SERVICE.getValue())
                    .eq(CompareAlias.valueOf(TsDirectStoreUser.Fields.state, "e"), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())
                    .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
            );
            for(TvDirectStoreUser m:storeUsers){
                m.setOnlineState(memCache.existsInServer(m.getType()+"-" + m.getUserId())?
                        CommonEnumContainer.OnlineState.ON_LINE.getValue(): CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
            }
            funds.setCustomerServers(storeUsers);
            funds.setManagers(respPeopleInfoMapper.selectManager(
                    WrapperFactory.multiQueryWrapper(TbPfundManager.class)
                            .select("e.personId,mp.name realName,mb.position")
                            .leftJoin(ClassAssociateTableInfo.valueOf(TbMdPeople.class,"mp"), mp->
                                    mp.eqc(CompareAlias.valueOf("mp.personId"),CompareAlias.valueOf("e.personId"))
                                    )
                            .leftJoin(ClassAssociateTableInfo.valueOf(TbPeopleManage.class,"pm"), pm->
                                    pm.eqc(CompareAlias.valueOf("pm.personId"),CompareAlias.valueOf("e.personId"))
                                    )
                            .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"), mb->
                                    mb.eqc(CompareAlias.valueOf("mb.id"),CompareAlias.valueOf("pm.userId"))
                            )
                    .eq(CompareAlias.valueOf("e.securityId"),funds.getSecurityId())
            ));
        }
        return Result.success(pageList);
    }
    /**
     * 直营店装潢列表
     * @param req
     * @return
     */
    @ApiOperation(value = "直营店装潢列表")
    @GetMapping("design/list")
    public Result<List<TrStoreDesign>> storeDesignList(TqStoreDesign req){
        List<TrStoreDesign> storeDesigns = respOrgInfoMapper.selectStoreDesignList(
                WrapperFactory.multiQueryWrapper(TbDirectStoreDesign.class)
                        .select("e.title,e.content,e.subTitle")
                        .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
                        .eq(CompareAlias.valueOf("e.visible"),true)
                        .eq(CompareAlias.valueOf("e.auditState"), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())
                        .eq(CompareAlias.valueOf("e.deltag"),false)
                        .orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf("e.sort")))
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")))
                        .page(new PageInfo())
        );
        return Result.success(storeDesigns);
    }
}

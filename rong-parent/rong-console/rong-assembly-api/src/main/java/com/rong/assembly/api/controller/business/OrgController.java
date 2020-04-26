package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.mapper.*;
import com.rong.assembly.api.module.request.TqListOrg;
import com.rong.assembly.api.module.request.TqOrgInfo;
import com.rong.assembly.api.module.request.buz.*;
import com.rong.assembly.api.module.response.TrFavUser;
import com.rong.assembly.api.module.response.org.TrRespOrg;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.assembly.api.module.response.product.TrFunds;
import com.rong.assembly.api.module.response.product.TrSummaryNav;
import com.rong.assembly.api.module.response.store.TrStoreDesign;
import com.rong.assembly.api.service.OrgService;
import com.rong.assembly.api.service.ProductService;
import com.rong.assembly.api.util.SelectAliasUtil;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbOrgManage;
import com.rong.fundmanage.module.entity.TbOrgProxy;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentGr;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNavGr;
import com.rong.fundmanage.module.query.TsOrgProxy;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.util.MemberUtil;
import com.rong.store.module.entity.TbDirectStoreDesign;
import com.rong.store.module.entity.TbDirectStoreOrg;
import com.rong.store.module.query.TsDirectStoreUser;
import com.rong.store.module.view.TvDirectStoreUser;
import com.rong.store.service.DirectStoreUserService;
import com.rong.tong.fund.module.entity.TbFund;
import com.rong.tong.fund.module.entity.TbFundAdjNav;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.fund.module.entity.TbFundNav;
import com.rong.tong.fund.module.query.TsFund;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import com.rong.tong.pfunds.module.entity.TbPfundNav;
import com.rong.tong.pfunds.module.query.TsPfund;
import com.rong.tong.pfunds.module.view.TvHisNav;
import com.rong.user.module.entity.TbUserOrgFav;
import com.vitily.mybatis.core.entity.FieldField;
import com.vitily.mybatis.core.entity.FieldValue;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Api(tags = "机构相关")
@RestController
@RequestMapping("org")
public class OrgController {
    @Autowired
    private OrgService orgService;
    @Autowired
    private ProductService productService;
    @Autowired
    private DirectStoreUserService directStoreUserService;
    @Autowired
    private CommonServiceCache commonServiceCache;

    @Autowired
    private RespOrgInfoMapper respOrgInfoMapper;
    @Autowired
    private RespUserFavMapper respUserFavMapper;
    @Autowired
    private RespPeopleInfoMapper respPeopleInfoMapper;
    @Autowired
    private RespTrFundsMapper respTrFundsMapper;
    @Autowired
    private GeneralMapper generalMapper;

    /**
     * 公司详情
     * @param req
     * @return
     */
    @ApiOperation(value = "公司详情")
    @GetMapping("index/info")
    public Result<TrRespOrg> index(TqOrgInfo req){
        //
        TrRespOrg org;
        CommonEnumContainer.OrgType type = orgService.getOrgType(req.getPartyId());
        if(type == CommonEnumContainer.OrgType.PRIVATE_PLACEMENT){
            org = orgService.selectPriOrgInfo(req);
            org.setManagers(orgService.selectManagerOfPriOrg(req));
        }else{
            org = orgService.selectRaisedOrgInfo(req);
            org.setManagers(orgService.selectManagerOfRaisedOrg(req));
        }
        org.setOrgType(type.getValue());
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        if(org.isStoreUser()) {
            //获取直营店信息

            org.setStoreInfo(respOrgInfoMapper.selectStoreInfo(WrapperFactory.multiQueryWrapper(TbDirectStoreOrg.class)
                .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
            ));

            //获取客服列表
            List<TvDirectStoreUser> storeUsers = directStoreUserService.selectViewList(directStoreUserService.getMultiCommonWrapper()
                    .eq(CompareAlias.valueOf(TsDirectStoreUser.Fields.partyId), req.getPartyId())
                    .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.StoreUserType.SERVICE.getValue())
                    .eq(CompareAlias.valueOf(TsDirectStoreUser.Fields.state, "e"), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())
                    .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
            );
            for(TvDirectStoreUser m:storeUsers){
                m.setOnlineState(memCache.existsInServer(m.getType()+"-" + m.getUserId())?
                        CommonEnumContainer.OnlineState.ON_LINE.getValue(): CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
            }
            org.setCustomerServers(storeUsers);
            //获取装潢信息列表
            List<TrStoreDesign> storeDesigns = respOrgInfoMapper.selectStoreDesignList(
                    WrapperFactory.multiQueryWrapper(TbDirectStoreDesign.class)
                        .select("e.title,e.content")
                        .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
                        .eq(CompareAlias.valueOf("e.visible"),true)
                        .eq(CompareAlias.valueOf("e.deltag"),false)
                        .eq(CompareAlias.valueOf("e.auditState"), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())
                        .orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf("e.sort")))
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")))
                        .page(new PageInfo())
            );
            org.setStoreDesigns(storeDesigns);
        }
        for(TrManager people:org.getManagers()){{
            if(null != people.getUserId()) {
                people.setOnlineState(memCache.existsInServer(people.getType() + "-" + people.getUserId()) ?
                        CommonEnumContainer.OnlineState.ON_LINE.getValue() : CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
            }
        }}
        return Result.success(org);
    }

    /**
     * 热门机构
     * @param req
     * @return
     */
    @ApiOperation(value = "热门机构")
    @GetMapping("hotSearch/list")
    public Result<TvPageList<TrRespOrg>> recommendUser(TqListOrg req){
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbOrgManage.class)
                .select("e.partyId,mi.partyShortName,mi.partyFullName")
                .select0(SelectAliasUtil.getFavOfOrg(req.getLoginUserId()))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), x->x.eqc(CompareAlias.valueOf("mi.partyId"),CompareAlias.valueOf("e.partyId")))
                .eq(CompareAlias.valueOf("e.hotSearch"),true)
                .eq(CompareAlias.valueOf("e.visible"),true)
                .eq(CompareAlias.valueOf("e.deltag"),false)
                .page(pageInfo)
                ;
        pageInfo.setRecordCount(respOrgInfoMapper.selectMultiTableCount(queryWrapper));
        TvPageList<TrRespOrg> pageList = new TvPageList<>();
        pageList.setPageInfo(pageInfo);
        pageList.setList(respOrgInfoMapper.selectViewList(queryWrapper));
        pageList.getList().forEach(org->
        {
            org.setProxys(respPeopleInfoMapper.selectTrRespUsers(
                    WrapperFactory.multiQueryWrapper(TbOrgProxy.class)
                            .selectAllFiels(false)
                            .select("e.userId,mb.userName,mb.realName,mb.position position,mb.nickName nickName,mb.headPortrait headPortrait")
                            .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),
                                    x->x.eqc(
                                            CompareAlias.valueOf(TsMemBase.Fields.id,"mb"),
                                            CompareAlias.valueOf(TsOrgProxy.Fields.userId,"e")))
                            .where(FieldValue.fromCondition("e.partyId.eq",org.getPartyId()))
            ));
        });
        return Result.success(pageList);
    }

    /**
     * 旗下产品列表
     * @param req
     * @return
     */
    @ApiOperation(value = "旗下产品列表")
    @GetMapping("fund/list")
    public Result<TvPageList<TrFunds>> fundList(TqFundsOfOrg req){
        CommonEnumContainer.OrgType type = orgService.getOrgType(req.getPartyId());
        if(type == CommonEnumContainer.OrgType.PRIVATE_PLACEMENT){
            return Result.success(productService.priFundsOfOrg(req));
        }
        return Result.success(productService.raisedFundsOfOrg(req));
    }

    /**
     * 收藏该机构的用户列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏机构的用户列表")
    @GetMapping("fav/list")
    public Result<TvPageList<TrFavUser>> favList(TqFavUserListOfOrg req){
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbUserOrgFav.class)
                .selectAllFiels(false)
                .select("m.userName,m.realName,m.headPortrait,e.userId")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"m"), x->
                        x.eqc(CompareAlias.valueOf("m.id"),CompareAlias.valueOf("e.userId"))
                )
                .where(FieldValue.fromCondition("e.partyId.eq",req.getPartyId()))
                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                .orderBy(OrderBy.valueOf(Order.DESC, SelectAlias.valueOf("e.id")))
                .page(pageInfo)
                ;
        pageInfo.setRecordCount(respUserFavMapper.selectMultiTableCount(queryWrapper));
        TvPageList<TrFavUser> outList = new TvPageList<>();
        outList.setPageInfo(pageInfo);
        outList.setList(respUserFavMapper.selectFavUsers(queryWrapper));

        for(TrFavUser user:outList.getList()){
            user.setRealName(StringUtil.markLastName(user.getRealName()));
            user.setUserName(StringUtil.markCenterPhone(user.getUserName()));
        }
        return Result.success(outList);
    }


    /**
     * 机构收益走势图
     * @param req
     * @return
     */
    @ApiOperation(value = "机构收益走势图")
    @GetMapping("accumNavTrend")
    public Result<List<TvHisNav>> accumNavTrend(TqAccumNavTrendOfOrg req) {
        //先看下是公募还是私募
        boolean priOrg = generalMapper.count(WrapperFactory.multiQueryWrapper(TbPfundInstInfo.class)
            .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
        ) > 0;
        MultiTableQueryWrapper queryWrapper;
        MultiTableQueryWrapper firstWrapper;
        if(priOrg){
            firstWrapper = WrapperFactory.multiQueryWrapper(TbPfundNav.class)
                    .selectAllFiels(false)
                    .select0(SelectAlias.valueOf("avg(e.ADJ_NAV) adjNav", true)
                            , SelectAlias.valueOf("avg(e.ACCUM_NAV) accumNav", true)
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class, "f"), f ->
                            f.where(FieldField.fromCondition("f.securityId.eq", "e.securityId"))
                    )
                    .eq(CompareAlias.valueOf(TsPfund.Fields.investConsultant, "f"), req.getPartyId())
            ;
        }else{
            firstWrapper = WrapperFactory.multiQueryWrapper(TbFundNav.class)
                    .selectAllFiels(false)
                    .select0(SelectAlias.valueOf("avg(fa.ADJ_NAV) adjNav", true)
                            , SelectAlias.valueOf("avg(e.ACCUM_NAV) accumNav", true)
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFundClass.class, "fc"), fc ->
                            fc.where(FieldField.fromCondition("fc.securityId.eq", "e.securityId"))
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFundAdjNav.class, "fa"), fa ->
                            fa
                                    .where(FieldField.fromCondition("fa.securityId.eq", "e.securityId"))
                                    .where(FieldField.fromCondition("fa.endDate.eq", "e.endDate"))
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFund.class, "f"), f ->
                            f.where(FieldField.fromCondition("f.fundId.eq", "fc.fundId"))
                    )
                    .eq(CompareAlias.valueOf(TsFund.Fields.managementCompany, "f"), req.getPartyId())
            ;
        }
        if (null != req.getEndDate()) {
            firstWrapper.le(CompareAlias.valueOf("e.endDate"), req.getEndDate());
        }
        if (null != req.getStartDate()) {
            firstWrapper.ge(CompareAlias.valueOf("e.endDate"), req.getStartDate());
        }
        firstWrapper
                .groupBy(SelectAlias.valueOf("e.endDate"))
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.endDate")))
        ;
        TvHisNav hisNav = respTrFundsMapper.selectHisNavOne(firstWrapper);
        if(null == hisNav){
            return Result.success(Collections.emptyList());
        }
        if(null == hisNav.getAdjNav()){
            hisNav.setAdjNav(BigDecimal.ZERO);
        }
        if(null == hisNav.getAccumNav()){
            hisNav.setAccumNav(BigDecimal.ZERO);
        }
        if(priOrg) {
            queryWrapper = WrapperFactory.multiQueryWrapper(TbPfundNav.class)
                    .selectAllFiels(false)
                    .select("e.endDate")
                    .select0(
                            SelectAlias.valueOf("avg(e.nav) nav", true)
                            , SelectAlias.valueOf("avg(e.ADJ_NAV) adjNav", true)
                            , SelectAlias.valueOf("avg(e.ACCUM_NAV) accumNav", true)
                            ,SelectAlias.valueOf("concat(truncate((avg(e.ADJ_NAV) - "+hisNav.getAdjNav()+") *100,2),'%') adjNavChange",true)
                            ,SelectAlias.valueOf("concat(truncate((avg(e.ACCUM_NAV) - "+hisNav.getAccumNav()+") *100,2),'%') accumNavChange",true)
                            , SelectAlias.valueOf("concat(truncate(avg(e.RETURN_RATE) *100,2),'%') returnRate", true)
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbPfund.class, "f"), f ->
                            f.where(FieldField.fromCondition("f.securityId.eq", "e.securityId"))
                    )
                    .eq(CompareAlias.valueOf(TsPfund.Fields.investConsultant, "f"), req.getPartyId())
            ;
        }else{
            queryWrapper = WrapperFactory.multiQueryWrapper(TbFundNav.class)
                    .selectAllFiels(false)
                    .select("e.endDate")
                    .select0(
                            SelectAlias.valueOf("avg(e.nav) nav", true)
                            , SelectAlias.valueOf("avg(fa.ADJ_NAV) adjNav", true)
                            , SelectAlias.valueOf("avg(e.ACCUM_NAV) accumNav", true)
                            ,SelectAlias.valueOf("concat(truncate((avg(fa.ADJ_NAV) - "+hisNav.getAdjNav()+") *100,2),'%') adjNavChange",true)
                            ,SelectAlias.valueOf("concat(truncate((avg(e.ACCUM_NAV) - "+hisNav.getAccumNav()+") *100,2),'%') accumNavChange",true)
                            , SelectAlias.valueOf("concat(truncate(avg(fa.RETURN_RATE) *100,2),'%') returnRate", true)
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFundClass.class, "fc"), fc ->
                            fc.where(FieldField.fromCondition("fc.securityId.eq", "e.securityId"))
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFundAdjNav.class, "fa"), fa ->
                            fa
                                    .where(FieldField.fromCondition("fa.securityId.eq", "e.securityId"))
                                    .where(FieldField.fromCondition("fa.endDate.eq", "e.endDate"))
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFund.class, "f"), f ->
                            f.where(FieldField.fromCondition("f.fundId.eq", "fc.fundId"))
                    )
                    .eq(CompareAlias.valueOf(TsFund.Fields.managementCompany, "f"), req.getPartyId())
            ;
        }
        queryWrapper
                .groupBy(SelectAlias.valueOf("e.endDate"))
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.endDate")))
        ;
        if (null != req.getEndDate()) {
            queryWrapper.le(CompareAlias.valueOf("e.endDate"), req.getEndDate());
        }
        if (null != req.getStartDate()) {
            queryWrapper.ge(CompareAlias.valueOf("e.endDate"), req.getStartDate());
        }
        return Result.success(respTrFundsMapper.selectHisNavList(queryWrapper));
    }

    /**
     * 企业收益统计（成立以来、近三年、近一年、近半年、近三个月）
     * @param req
     * @return
     */
    @ApiOperation(value = "企业收益统计（成立以来、近三年、近一年、近半年、近三个月）")
    @GetMapping("summaryNav")
    public Result<TrSummaryNav> summaryNav(TqSummaryOrgNav req) {
        //先看下是公募还是私募
        boolean priOrg = generalMapper.count(WrapperFactory.multiQueryWrapper(TbPfundInstInfo.class)
                .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
        ) > 0;
        Class<?> tbClass = priOrg ? TbPrivateFundsCurrentGr.class: TbRaisedFundCurrentNavGr.class;
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(tbClass).select0(
                        SelectAlias.valueOf(" avg(return_rate_est) returnRateEst",true),
                        SelectAlias.valueOf(" avg(return_rate_3y) lastedThreeYear",true),
                        SelectAlias.valueOf(" avg(return_rate_1y) lastedYear",true),
                        SelectAlias.valueOf(" avg(return_rate_6m) lastedHalfYear",true),
                        SelectAlias.valueOf(" avg(return_rate_3m) lastedThreeMonths",true)
                )
                .eq(CompareAlias.valueOf("e.partyId"),req.getPartyId())
        ;
        TrSummaryNav summaryNav = respTrFundsMapper.selectSummaryNav(queryWrapper);
        return Result.success(summaryNav);
    }

    /**
     * 推荐机构
     * @param req
     * @return
     */
    @ApiOperation(value = "推荐机构列表")
    @GetMapping("recommend/list")
    public Result<List<TrRespOrg>> recommendList(TqRecommendParty req){
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbOrgManage.class)
                .selectAllFiels(true)
                .select("mi.partyFullName,mi.partyShortName,e.partyId,mi.regDate recordDate")
                .select0(
                        //近一年年化收益
                        SelectAlias.valueOf("case when e.type = 0 then (select avg(return_of_latest_year) from tb_private_funds_current_perf where PARTY_ID=e.PARTY_ID) else (select avg(return_rate_1y)from tb_raised_fund_current_performance where party_id=e.party_id) end as returnOfLatestYear",true)
                        ,//累计收益
                        SelectAlias.valueOf("case when e.type = 0 then (select avg(return_accum) from tb_private_funds_current_income where PARTY_ID=e.PARTY_ID) else null end as returnAccum",true)
                        ,//年化收益
                        SelectAlias.valueOf("case when e.type = 0 then (select avg(return_a) from tb_private_funds_current_income where PARTY_ID=e.PARTY_ID) else null end as returnA",true)
                        ,//核心人物
                        SelectAlias.valueOf("case when e.type = 0 then (select LEGAL_REP from `tong-rong`.pfund_inst_info where PARTY_ID=e.PARTY_ID limit 1) else (select GEN_MANA from `tong-rong`.fund_inst_info where party_id=e.party_id) end as keyPerson",true),
                        //核心人物id
                        //SelectAlias.valueOf("case when e.type = 0 then (select avg(return_a) from tb_private_funds_current_income where PARTY_ID=e.PARTY_ID) else () end as keyPerson",true),
                        //代表作(公募找不到？)
                        SelectAlias.valueOf("case when e.type = 0 then (select a.SECURITY_ID from `tong-rong`.pfund_inst_rep a where a.PARTY_ID=e.PARTY_ID order by a.id desc limit 1) else null end as repSecurityId",true)
                        ,//代表作id(公募找不到？)
                        SelectAlias.valueOf("case when e.type = 0 then (select b.SEC_FULL_NAME from `tong-rong`.pfund_inst_rep a left join `tong-rong`.md_security b on a.security_id=b.security_id where a.PARTY_ID=e.PARTY_ID order by a.id desc limit 1) else null end as repSecurityFullName",true)


                )
                .select0(SelectAliasUtil.getFavOfOrg(req.getLoginUserId()))
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"),
                        x->x
                                .where(FieldField.fromCondition("e.partyId.eq","mi.partyId"))
                )
                .where(FieldValue.fromCondition("e.recommend.eq", CommonEnumContainer.YesOrNo.RIGHT.getValue()))
                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                .page(req.getPageInfo())
                ;
        List<TrRespOrg> respOrgs = respOrgInfoMapper.selectViewList(queryWrapper);
        return Result.success(respOrgs);
    }

}

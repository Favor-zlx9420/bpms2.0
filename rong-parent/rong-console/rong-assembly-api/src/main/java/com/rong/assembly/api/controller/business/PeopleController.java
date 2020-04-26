package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.mapper.*;
import com.rong.assembly.api.module.request.TqListUser;
import com.rong.assembly.api.module.request.TqPeopleInfo;
import com.rong.assembly.api.module.request.buz.*;
import com.rong.assembly.api.module.response.TrFavUser;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.assembly.api.module.response.product.TrFunds;
import com.rong.assembly.api.module.response.product.TrSummaryNav;
import com.rong.assembly.api.service.OrgService;
import com.rong.assembly.api.service.PeopleService;
import com.rong.assembly.api.service.ProductService;
import com.rong.assembly.api.util.SelectAliasUtil;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbPeopleManage;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentGr;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNavGr;
import com.rong.fundmanage.service.PeopleManageService;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.util.MemberUtil;
import com.rong.tong.fund.module.entity.TbFundAdjNav;
import com.rong.tong.fund.module.entity.TbFundManagerNew;
import com.rong.tong.fund.module.entity.TbFundNav;
import com.rong.tong.fund.module.query.TsFundManagerNew;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
import com.rong.tong.pfunds.module.entity.TbPfundNav;
import com.rong.tong.pfunds.module.query.TsPfundManager;
import com.rong.tong.pfunds.module.view.TvHisNav;
import com.rong.user.module.entity.TbUserPeopleFav;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Api(tags = "人物相关")
@RestController
@RequestMapping("people")
public class PeopleController {
    @Autowired
    private PeopleManageService peopleManageService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private RespPeopleInfoMapper respPeopleInfoMapper;
    @Autowired
    private RespTrFundsMapper respTrFundsMapper;
    @Autowired
    private RespUserFavMapper respUserFavMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private CommonServiceCache commonServiceCache;
    /**
     * 热门用户
     * @param req
     * @return
     */
    @ApiOperation(value = "热门嘉宾")
    @GetMapping("hotSearch/list")
    public Result<TvPageList<TrManager>> recommendUser(TqListUser req){
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = peopleManageService.getMultiCommonWrapper()
                .select0(SelectAliasUtil.getFavOfPeople(req.getLoginUserId()))
                .eq(CompareAlias.valueOf("e.hotSearch"),true)
                .eq(CompareAlias.valueOf("e.visible"),true)
                .eq(CompareAlias.valueOf("e.deltag"),false)
                .page(pageInfo)
        ;
        pageInfo.setRecordCount(respPeopleInfoMapper.selectMultiTableCount(queryWrapper));
        TvPageList<TrManager> pageList = new TvPageList<>();
        pageList.setPageInfo(pageInfo);
        pageList.setList(respPeopleInfoMapper.selectViewList(queryWrapper));

        return Result.success(pageList);
    }
    /**
     * 旗下基金
     * @param req
     * @return
     */
    @ApiOperation(value = "旗下基金列表")
    @GetMapping("fund/list")
    public Result<TvPageList<TrFunds>> fundList(TqFundsOfManager req){
        if(CommonUtil.isEqual(req.getType(), CommonEnumContainer.ProductType.PRIVATE_PLACEMENT.getValue())){
            return Result.success(productService.priFundsOfManager(req));
        }
        return Result.success(productService.raisedFundsOfManager(req));
    }

    /**
     * 收藏该用户的用户列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏基金经理的用户列表")
    @GetMapping("fav/list")
    public Result<TvPageList<TrFavUser>> favList(TqFavUserListOfManager req){
        PageInfo pageInfo = req.getPageInfo();
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbUserPeopleFav.class)
                .selectAllFiels(false)
                .select("m.userName,m.realName,m.headPortrait,e.userId")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"m"), x->
                        x.eqc(CompareAlias.valueOf("m.id"),CompareAlias.valueOf("e.userId"))
                )
                .where(FieldValue.fromCondition("e.personId.eq",req.getPersonId()))
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
     * 用户详情
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "基金经理详情")
    @GetMapping("detail/info")
    public Result<TrManager> peopleDetail(TqPeopleInfo req){
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        TrManager manager = peopleService.info(req);
        TqFundsOfManager tqFundsOfManager = new TqFundsOfManager();
        BeanUtils.copyProperties(req,tqFundsOfManager);
        manager.setPriFunds(productService.priFundsOfManager(tqFundsOfManager).getList());
        manager.setRaisedFunds(productService.raisedFundsOfManager(tqFundsOfManager).getList());
        manager.setServiceOrgs(orgService.getServiceOrgOfManager(req));
        manager.setOnlineState(memCache.existsInServer(manager.getType() + "-" + manager.getUserId()) ?
                CommonEnumContainer.OnlineState.ON_LINE.getValue() : CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
        return Result.success(manager);
    }

    /**
     * 基金经理收益走势图
     * @param req
     * @return
     */
    @ApiOperation(value = "基金经理收益走势图")
    @GetMapping("accumNavTrend")
    public Result<List<TvHisNav>> accumNavTrend(TqAccumNavTrendOfManager req) {
        Date now = new Date();
        Date beginDate = req.getStartDate();
        Date endDate = req.getEndDate();
        if(null == beginDate){
            beginDate = DateUtil.addYear(now,-40);
        }
        if(null == endDate){
            endDate = now;
        }
        //查出第一笔
        TvHisNav hisNav = respTrFundsMapper.selectHisNavOne(WrapperFactory.multiQueryWrapper(TbPfundNav.class)
                .selectAllFiels(false)
                .select0(SelectAlias.valueOf("avg(e.ADJ_NAV) adjNav", true)
                        , SelectAlias.valueOf("avg(e.ACCUM_NAV) accumNav", true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbPfundManager.class, "f"), f ->
                        f.where(FieldField.fromCondition("f.securityId.eq", "e.securityId"))
                )
                .eq(CompareAlias.valueOf(TsPfundManager.Fields.personId, "f"), req.getPersonId())
                .groupBy(SelectAlias.valueOf("e.endDate"))
                .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.endDate")))
        );
        if(hisNav != null) {
            if(null == hisNav.getAdjNav()){
                hisNav.setAdjNav(BigDecimal.ZERO);
            }
            if(null == hisNav.getAccumNav()){
                hisNav.setAccumNav(BigDecimal.ZERO);
            }
            MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbPfundNav.class)
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
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbPfundManager.class, "f"), f ->
                            f.where(FieldField.fromCondition("f.securityId.eq", "e.securityId"))
                    )
                    .eq(CompareAlias.valueOf(TsPfundManager.Fields.personId, "f"), req.getPersonId())
                    .groupBy(SelectAlias.valueOf("e.endDate"))
                    .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.endDate")));
                    queryWrapper.le(CompareAlias.valueOf("e.endDate"), endDate);
                    queryWrapper.ge(CompareAlias.valueOf("e.endDate"), beginDate);
            List<TvHisNav> hisNavs = respTrFundsMapper.selectHisNavList(queryWrapper);
            return Result.success(hisNavs);
        }else{
            hisNav = respTrFundsMapper.selectHisNavOne(WrapperFactory.multiQueryWrapper(TbFundNav.class)
                    .selectAllFiels(false)
                    .select0(SelectAlias.valueOf("avg(fa.ADJ_NAV) adjNav", true)
                            , SelectAlias.valueOf("avg(e.ACCUM_NAV) accumNav", true)
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFundAdjNav.class, "fa"), fa ->
                            fa
                                    .where(FieldField.fromCondition("fa.securityId.eq", "e.securityId"))
                                    .where(FieldField.fromCondition("fa.endDate.eq", "e.endDate"))
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFundManagerNew.class, "f"), f ->
                            f.where(FieldField.fromCondition("f.securityId.eq", "e.securityId"))
                    )
                    .eq(CompareAlias.valueOf(TsFundManagerNew.Fields.personId, "f"), req.getPersonId())
                    .groupBy(SelectAlias.valueOf("e.endDate"))
                    .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.endDate")))
                );
            if(null == hisNav){
                return Result.success();
            }
            if(null == hisNav.getAdjNav()){
                hisNav.setAdjNav(BigDecimal.ZERO);
            }
            if(null == hisNav.getAccumNav()){
                hisNav.setAccumNav(BigDecimal.ZERO);
            }
            MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbFundNav.class)
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
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFundAdjNav.class, "fa"), fa ->
                            fa
                                    .where(FieldField.fromCondition("fa.securityId.eq", "e.securityId"))
                                    .where(FieldField.fromCondition("fa.endDate.eq", "e.endDate"))
                    )
                    .leftJoin(ClassAssociateTableInfo.valueOf(TbFundManagerNew.class, "f"), f ->
                            f.where(FieldField.fromCondition("f.securityId.eq", "e.securityId"))
                    )
                    .eq(CompareAlias.valueOf(TsFundManagerNew.Fields.personId, "f"), req.getPersonId())
                    .groupBy(SelectAlias.valueOf("e.endDate"))
                    .orderBy(OrderBy.valueOf(Order.ASC, SelectAlias.valueOf("e.endDate")))
                    ;
            queryWrapper.le(CompareAlias.valueOf("e.endDate"), endDate);
            queryWrapper.ge(CompareAlias.valueOf("e.endDate"), beginDate);
            return Result.success(respTrFundsMapper.selectHisNavList(queryWrapper));
        }
    }

    /**
     * 基金经理收益统计（成立以来、近三年、近一年、近半年、近三个月）
     * @param req
     * @return
     */
    @ApiOperation(value = "基金经理收益统计（成立以来、近三年、近一年、近半年、近三个月）")
    @GetMapping("summaryNav")
    public Result<TrSummaryNav> summaryNav(TqSummaryPeopleNav req) {
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper(TbPrivateFundsCurrentGr.class).select0(
                SelectAlias.valueOf(" avg(return_rate_est) returnRateEst",true),
                SelectAlias.valueOf(" avg(return_rate_3y) lastedThreeYear",true),
                SelectAlias.valueOf(" avg(return_rate_1y) lastedYear",true),
                SelectAlias.valueOf(" avg(return_rate_6m) lastedHalfYear",true),
                SelectAlias.valueOf(" avg(return_rate_3m) lastedThreeMonths",true)
        )
                .eq(CompareAlias.valueOf("e.personId"),req.getPersonId())
                ;
        TrSummaryNav summaryNav0 = respTrFundsMapper.selectSummaryNav(queryWrapper);
        if(summaryNav0 != null){
            return Result.success(summaryNav0);
        }
        queryWrapper = WrapperFactory.multiQueryWrapper(TbRaisedFundCurrentNavGr.class).select0(
                SelectAlias.valueOf(" avg(return_rate_est) returnRateEst",true),
                SelectAlias.valueOf(" avg(return_rate_3y) lastedThreeYear",true),
                SelectAlias.valueOf(" avg(return_rate_1y) lastedYear",true),
                SelectAlias.valueOf(" avg(return_rate_6m) lastedHalfYear",true),
                SelectAlias.valueOf(" avg(return_rate_3m) lastedThreeMonths",true)
        )
                .eq(CompareAlias.valueOf("e.personId"),req.getPersonId())
        ;
        TrSummaryNav summaryNav1 = respTrFundsMapper.selectSummaryNav(queryWrapper);
        return Result.success(summaryNav1);
    }

    /**
     * 推荐基金经理列表
     * @param req
     * @return
     */
    @ApiOperation(value = "推荐基金经理列表")
    @GetMapping("recommend/list")
    public Result<List<TrManager>> recommendList(TqRecommendPeople req){
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        MultiTableQueryWrapper queryWrapper = peopleService.getCommonOfManager(TbPeopleManage.class,"e.person_id",req.getLoginUserId())
                .selectAllFiels(true)
                .select("mb.level,mb.headPortrait")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"),
                        x->x.where(FieldField.fromCondition("e.userId.eq","mb.id"))
                )
                .where(FieldValue.fromCondition("e.recommend.eq", CommonEnumContainer.YesOrNo.RIGHT.getValue()))
                .where(FieldValue.fromCondition("e.visible.eq", CommonEnumContainer.YesOrNo.RIGHT.getValue()))
                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                .page(req.getPageInfo())
                ;
        List<TrManager> respOrgs = respPeopleInfoMapper.selectViewList(queryWrapper);
        TqPeopleInfo tqPeopleInfo = new TqPeopleInfo();
        BeanUtils.copyProperties(req,tqPeopleInfo);
        for(TrManager manager:respOrgs){
            tqPeopleInfo.setPersonId(manager.getPersonId());
            manager.setServiceOrgs(orgService.getServiceOrgOfManager(tqPeopleInfo));
            manager.setOnlineState(memCache.existsInServer(manager.getType() + "-" + manager.getUserId()) ?
                    CommonEnumContainer.OnlineState.ON_LINE.getValue() : CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
        }
        return Result.success(respOrgs);
    }

}

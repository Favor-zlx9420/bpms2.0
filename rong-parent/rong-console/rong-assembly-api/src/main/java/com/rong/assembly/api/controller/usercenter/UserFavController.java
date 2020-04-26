package com.rong.assembly.api.controller.usercenter;

import com.rong.assembly.api.module.request.uc.fav.*;
import com.rong.assembly.api.module.response.people.TrFavManager;
import com.rong.assembly.api.module.response.product.TrFavFund;
import com.rong.assembly.api.service.PeopleService;
import com.rong.assembly.api.service.ProductService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import com.rong.fundmanage.module.query.TsSecurityManage;
import com.rong.fundmanage.module.request.TqSecurityManage;
import com.rong.fundmanage.service.SecurityManageService;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.roadshow.module.entity.TbUserRoadShowFav;
import com.rong.roadshow.module.query.TsRoadShowInfo;
import com.rong.roadshow.module.query.TsUserRoadShowFav;
import com.rong.roadshow.module.request.TqUserRoadShowFav;
import com.rong.roadshow.module.view.TvUserRoadShowFav;
import com.rong.roadshow.service.RoadShowInfoService;
import com.rong.roadshow.service.UserRoadShowFavService;
import com.rong.tong.fund.module.query.TsFundClass;
import com.rong.tong.fund.service.FundClassService;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.query.TsPfund;
import com.rong.tong.pfunds.service.MdInstitutionService;
import com.rong.tong.pfunds.service.MdPeopleService;
import com.rong.tong.pfunds.service.PfundService;
import com.rong.user.module.entity.TbUserOrgFav;
import com.rong.user.module.entity.TbUserPeopleFav;
import com.rong.user.module.entity.TbUserProFav;
import com.rong.user.module.request.TqUserOrgFav;
import com.rong.user.module.request.TqUserPeopleFav;
import com.rong.user.module.request.TqUserProFav;
import com.rong.user.module.view.TvUserOrgFav;
import com.rong.user.service.UserOrgFavService;
import com.rong.user.service.UserPeopleFavService;
import com.rong.user.service.UserProFavService;
import com.vitily.mybatis.core.entity.FieldValue;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.core.wrapper.update.UpdateWrapper;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "用户收藏中心")
@RestController
@RequestMapping("user/fav")
public class UserFavController {
    @Autowired
    private UserRoadShowFavService userRoadShowFavService;
    @Autowired
    private UserPeopleFavService userPeopleFavService;
    @Autowired
    private UserOrgFavService userOrgFavService;
    @Autowired
    private UserProFavService userProFavService;
    @Autowired
    private RoadShowInfoService roadShowInfoService;
    @Autowired
    private MdInstitutionService mdInstitutionService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private SecurityManageService securityManageService;
    @Autowired
    private PfundService pfundService;
    @Autowired
    private FundClassService fundClassService;
    @Autowired
    private MdPeopleService mdPeopleService;
    @Autowired
    private ProductService productService;


    //region 路演

    /**
     *
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏的路演列表")
    @GetMapping("roadShow/list")
    public Result<TvPageList<TvUserRoadShowFav>> roadShowList(TqFavRoadShowList req){
        MultiTableQueryWrapper queryWrapper = userRoadShowFavService.getMultiCommonWrapper();
        queryWrapper
                .select("rs.updateDate")
                .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
                .page(req.getPageInfo())
                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")))
                ;
        return Result.success(userRoadShowFavService.selectPageList(queryWrapper));
    }
    /**
     *
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏路演")
    @PostMapping("roadShow/add")
    public Result<Boolean> addRoadShow(@RequestBody TqFavRoadShowOpera req){
        TbRoadShowInfo roadShowInfo = roadShowInfoService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getRoadShowId()
                , TsRoadShowInfo.Fields.id
                , TsRoadShowInfo.Fields.state
                , TsRoadShowInfo.Fields.deltag
        ));
        if(null == roadShowInfo || roadShowInfo.getDeltag() == true
                || !CommonUtil.isEqual(roadShowInfo.getState(), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())){
            return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,"路演不存在");
        }
        TbUserRoadShowFav has = userRoadShowFavService.selectOne(
                new QueryWrapper()
                        .select(TsUserRoadShowFav.Fields.id, TsUserRoadShowFav.Fields.deltag)
                        .eq(TsUserRoadShowFav.Fields.userId,req.getUserId())
                        .eq(TsUserRoadShowFav.Fields.roadShowId,req.getRoadShowId())
        );
        if(has == null){
            TbUserRoadShowFav fav = new TbUserRoadShowFav()
                    .setRoadShowId(req.getRoadShowId())
                    .setUserId(req.getUserId())
                    ;
            userRoadShowFavService.insert(new TqUserRoadShowFav().setEntity(fav));
        }else{
            userRoadShowFavService.updateSelectItem(new UpdateWrapper()
                .update(
                        Elements.valueOf(TsUserRoadShowFav.Fields.updateDate,new Date()),
                        Elements.valueOf(TsUserRoadShowFav.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
                )
                    .eq(TsUserRoadShowFav.Fields.id,has.getId())
            );
        }
        return Result.success(Boolean.TRUE);
    }
    /**
     *
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "取消收藏路演")
    @PostMapping("roadShow/remove")
    public Result<Boolean> removeRoadShow(@RequestBody TqFavRoadShowOpera req){
        TbRoadShowInfo roadShowInfo = roadShowInfoService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getRoadShowId()
                , TsRoadShowInfo.Fields.id
                , TsRoadShowInfo.Fields.state
                , TsRoadShowInfo.Fields.deltag
        ));
        if(null == roadShowInfo || roadShowInfo.getDeltag() == true
                || !CommonUtil.isEqual(roadShowInfo.getState(), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())){
            return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,"路演不存在");
        }
        TbUserRoadShowFav has = userRoadShowFavService.selectOne(
                new QueryWrapper()
                        .select(TsUserRoadShowFav.Fields.id, TsUserRoadShowFav.Fields.deltag)
                        .eq(TsUserRoadShowFav.Fields.userId,req.getUserId())
                        .eq(TsUserRoadShowFav.Fields.roadShowId,req.getRoadShowId())
        );
        if(has != null){
            userRoadShowFavService.updateSelectItem(new UpdateWrapper()
                    .update(
                            Elements.valueOf(TsUserRoadShowFav.Fields.updateDate,new Date()),
                            Elements.valueOf(TsUserRoadShowFav.Fields.deltag, CommonEnumContainer.Deltag.DELETED.getValue())
                    )
                    .eq(TsUserRoadShowFav.Fields.id,has.getId()))
            ;
        }
        return Result.success(Boolean.TRUE);
    }

    //endregion

    //region 机构


    /**
     * 收藏的机构列表
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏的机构列表")
    @GetMapping("org/list")
    public Result<TvPageList<TvUserOrgFav>> orgList(TqFavOrgList req){
        if(null == req.getPageInfo()){
            req.setPageInfo(new PageInfo());
        }
        MultiTableQueryWrapper queryWrapper = userOrgFavService.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mi.partyShortName,mi.partyFullName")
                .select0(
                        //累计收益
                        SelectAlias.valueOf("(select avg(return_accum) from tb_private_funds_current_income where PARTY_ID=e.PARTY_ID) as returnAccum",true)
                        ,//年化收益
                        SelectAlias.valueOf("(select avg(return_a) from tb_private_funds_current_income where PARTY_ID=e.PARTY_ID) as returnA",true),
                        //私募净值
                        SelectAlias.valueOf("(select avg(nav) from tb_private_funds_current_nav where PARTY_ID=e.PARTY_ID) as priNav",true),
                        //私募累计净值
                        SelectAlias.valueOf("(select avg(accum_nav) from tb_private_funds_current_nav where PARTY_ID=e.PARTY_ID) priAccumNav",true),
                        //公募净值
                        SelectAlias.valueOf("(select avg(nav) from tb_raised_fund_current_nav where PARTY_ID=e.PARTY_ID) as raisedNav",true),
                        //公募累计净值
                        SelectAlias.valueOf("(select avg(accum_nav) from tb_raised_fund_current_nav where PARTY_ID=e.PARTY_ID) as raisedAccumNav",true)
                        //私募今年以来收益
                        ,SelectAlias.valueOf("(select avg(return_of_this_year) from tb_private_funds_current_perf where PARTY_ID=e.PARTY_ID) as returnOfThisYear0",true)
                        //公募今年以来收益
                        ,SelectAlias.valueOf("(select avg(return_rate_ytd) from tb_raised_fund_current_nav_gr where PARTY_ID=e.PARTY_ID) as returnOfThisYear1",true)
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), mi->
                        mi.eqc(CompareAlias.valueOf("mi.partyId"),CompareAlias.valueOf("e.partyId"))
                        )
                .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                .eq(CompareAlias.valueOf("e.deltag"),false)
                .page(req.getPageInfo())
                ;
        if(CommonUtil.isNormalSql(req.getPageInfo().getSortField())){
            queryWrapper.orderBy(OrderBy.valueOf(Order.valueOf(req.getPageInfo().getSortDistanct().toUpperCase()), SelectAlias.valueOf(req.getPageInfo().getSortField())));
        }else{
            queryWrapper.orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")));
        }
        return Result.success(userOrgFavService.selectPageList(queryWrapper));
    }
    /**
     * 收藏机构
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏机构")
    @PostMapping("org/add")
    public Result<Boolean> addOrgs(@RequestBody TqFavOrgOpera req){
        TbUserOrgFav has = userOrgFavService.selectOne(
                new QueryWrapper()
                        .select("id,deltag")
                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
                        .where(FieldValue.fromCondition("partyId.eq",req.getPartyId()))
        );
        if(has == null){
            checkOrgExists(req.getPartyId());
            TbUserOrgFav fav = new TbUserOrgFav()
                    .setPartyId(req.getPartyId())
                    .setUserId(req.getUserId())
                    ;
            userOrgFavService.insert(new TqUserOrgFav().setEntity(fav));
        }else{
            has.setUpdateDate(new Date());
            has.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
            userOrgFavService.updateSelectiveByPrimaryKey(has);
        }
        return Result.success(Boolean.TRUE);
    }
    /**
     * 取消收藏机构
     * @param req
     * @return
     */
    @ApiOperation(value = "取消收藏机构")
    @PostMapping("org/remove")
    public Result<Boolean> removeOrg(@RequestBody TqFavOrgOpera req){
        TbUserOrgFav has = userOrgFavService.selectOne(
                new QueryWrapper()
                        .select("id,deltag")
                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
                        .where(FieldValue.fromCondition("partyId.eq",req.getPartyId()))
        );
        if(has != null){
            has.setUpdateDate(new Date());
            has.setDeltag(CommonEnumContainer.Deltag.DELETED.getValue());
            userOrgFavService.updateSelectiveByPrimaryKey(has);
        }
        return Result.success(Boolean.TRUE);
    }

    private void checkOrgExists(Long partyId){
        if(mdInstitutionService.selectCount(new QueryWrapper()
                .where(FieldValue.fromCondition("partyId.eq",partyId))
        ) == 0){
            throw new NoExistsException("机构不存在");
        }
    }

    //endregion

    //region 机构人员


    /**
     * 收藏的产品经理
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏的产品经理")
    @GetMapping("manager/list")
    public Result<TvPageList<TrFavManager>> managerList(TqFavPeopleList req){
        return Result.success(peopleService.selectFavManager(req));
    }
    /**
     * 收藏经理
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏经理")
    @PostMapping("manager/add")
    public Result<Boolean> addManager(@RequestBody TqFavPeopleOpera req){
        TbUserPeopleFav has = userPeopleFavService.selectOne(
                new QueryWrapper()
                        .select("id,deltag")
                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
                        .where(FieldValue.fromCondition("personId.eq",req.getPersonId()))
        );
        if(has == null){
            checkManagerExists(req.getPersonId());
            TbUserPeopleFav fav = new TbUserPeopleFav()
                    .setPersonId(req.getPersonId())
                    .setUserId(req.getUserId())
                    ;
            userPeopleFavService.insert(new TqUserPeopleFav().setEntity(fav));
        }else{
            has.setUpdateDate(new Date());
            has.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
            userPeopleFavService.updateSelectiveByPrimaryKey(has);
        }
        return Result.success(Boolean.TRUE);
    }
    /**
     * 取消收藏经理
     * @param req
     * @return
     */
    @ApiOperation(value = "取消收藏经理")
    @PostMapping("manager/remove")
    public Result<Boolean> removeManager(@RequestBody TqFavPeopleOpera req){
        TbUserPeopleFav has = userPeopleFavService.selectOne(
                new QueryWrapper()
                        .select("id,deltag")
                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
                        .where(FieldValue.fromCondition("personId.eq",req.getPersonId()))
        );
        if(has != null){
            has.setUpdateDate(new Date());
            has.setDeltag(CommonEnumContainer.Deltag.DELETED.getValue());
            userPeopleFavService.updateSelectiveByPrimaryKey(has);
        }
        return Result.success(Boolean.TRUE);
    }

    private void checkManagerExists(Long personId){
        TbMdPeople people = mdPeopleService.selectOne(
                new QueryWrapper().select("personId")
                    .where(FieldValue.fromCondition("personId.eq",personId))
        );
        if(null == people){
            throw new NoExistsException("基金经理不存在");
        }
    }


    //endregion

    //region 基金

    /**
     * 收藏的产品列表
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏的产品列表")
    @GetMapping("funds/list")
    public Result<TvPageList<TrFavFund>> fundsList(TqFavProList req){
        return Result.success(productService.selectFavFunds(req));
    }
    /**
     * 收藏产品
     * @param req
     * @return
     */
    @ApiOperation(value = "收藏产品")
    @PostMapping("funds/add")
    public Result<Boolean> addFunds(@RequestBody TqFavProOpera req){
        TbUserProFav has = userProFavService.selectOne(
                new QueryWrapper()
                        .select("id,deltag,type")
                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
                        .where(FieldValue.fromCondition("securityId.eq",req.getSecurityId()))
        );
        if(has == null){
            //查看产品库里是否存在
            TbSecurityManage securityManage = securityManageService.selectOne(
                    WrapperFactory.queryWrapper().eq(TsSecurityManage.Fields.securityId,req.getSecurityId())
            );
            if(null == securityManage){
                securityManage = new TbSecurityManage();
                securityManage.setVisible(true);
                securityManage.setHotSearch(false);
                securityManage.setRecommend(false);
                securityManage.setSecurityId(req.getSecurityId());
                if(pfundService.selectCount(WrapperFactory.queryWrapper().eq(TsPfund.Fields.securityId,req.getSecurityId())) > 0){
                    securityManage.setType(CommonEnumContainer.ProductType.PRIVATE_PLACEMENT.getValue());
                }else if(fundClassService.selectCount(WrapperFactory.queryWrapper().eq(TsFundClass.Fields.securityId,req.getSecurityId())) > 0){
                    securityManage.setType(CommonEnumContainer.ProductType.PUBLIC_PLACEMENT.getValue());
                }else{
                    throw new NoExistsException("产品不存在");
                }
                securityManageService.insert(new TqSecurityManage().setEntity(securityManage));
            }
            TbUserProFav fav = new TbUserProFav()
                    .setSecurityId(req.getSecurityId())
                    .setUserId(req.getUserId())
                    .setType(securityManage.getType())
                    ;
            userProFavService.insert(new TqUserProFav().setEntity(fav));
        }else{
            has.setUpdateDate(new Date());
            has.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
            userProFavService.updateSelectiveByPrimaryKey(has);
        }
        return Result.success(Boolean.TRUE);
    }
    /**
     * 取消收藏产品
     * @param req
     * @return
     */
    @ApiOperation(value = "取消收藏产品")
    @PostMapping("funds/remove")
    public Result<Boolean> removeFunds(@RequestBody TqFavProOpera req){
        TbUserProFav has = userProFavService.selectOne(
                new QueryWrapper()
                        .select("id,deltag,type")
                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
                        .where(FieldValue.fromCondition("securityId.eq",req.getSecurityId()))
        );
        if(has != null){
            has.setUpdateDate(new Date());
            has.setDeltag(CommonEnumContainer.Deltag.DELETED.getValue());
            userProFavService.updateSelectiveByPrimaryKey(has);
        }
        return Result.success(Boolean.TRUE);
    }

    //endregion

}

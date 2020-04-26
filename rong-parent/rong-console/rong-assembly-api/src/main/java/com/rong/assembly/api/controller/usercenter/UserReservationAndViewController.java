package com.rong.assembly.api.controller.usercenter;

import com.rong.fundmanage.service.PeopleManageService;
import com.rong.roadshow.service.RoadShowInfoService;
import com.rong.roadshow.service.UserRoadShowViewService;
import com.rong.tong.pfunds.service.MdInstitutionService;
import com.rong.tong.pfunds.service.MdSecurityService;
import com.rong.user.service.UserOrgReservationService;
import com.rong.user.service.UserPeopleReservationService;
import com.rong.user.service.UserProReservationService;
import org.springframework.beans.factory.annotation.Autowired;

//@Api(tags = "预约路演中心")
//@RestController
//@RequestMapping("user/reservation")
public class UserReservationAndViewController {
    @Autowired
    private UserRoadShowViewService userRoadShowReservationService;
    @Autowired
    private RoadShowInfoService roadShowInfoService;
    @Autowired
    private UserOrgReservationService userOrgReservationService;
    @Autowired
    private UserPeopleReservationService userPeopleReservationService;
    @Autowired
    private UserProReservationService userProReservationService;
    @Autowired
    private MdSecurityService mdSecurityService;
    @Autowired
    private MdInstitutionService mdInstitutionService;
    @Autowired
    private PeopleManageService peopleManageService;
//
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "我预约的路演列表")
//    @GetMapping("roadShow/list")
//    public Result<TvPageList<TvUserRoadShowReservation>> roadShowList(TqReservationRoadShowList req){
//        if(null == req.getPageInfo()){
//            req.setPageInfo(new PageInfo());
//        }
//        MultiTableQueryWrapper<TbUserRoadShowReservation> queryWrapper = userRoadShowReservationService.getMultiCommonWrapper();
//        queryWrapper
//                .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
//                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.正常.getValue())
//                .ge(CompareAlias.valueOf("rs.showDate"), DateUtil.getCurDateTime(DateUtil.JSONINPUT_yyyy_MM_dd_HH_mm_ss_EN))
//                .page(req.getPageInfo())
//        ;
//        return Result.success(userRoadShowReservationService.selectPageList(queryWrapper));
//    }
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "预约/查看路演")
//    @PostMapping("roadShow/add")
//    public Result<Boolean> addRoadShow(@RequestBody TqReservationRoadShowOpera req){
//        TbRoadShowInfo roadShowInfo = roadShowInfoService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getRoadShowId()
//                , TsRoadShowInfo.Fields.id
//                ,TsRoadShowInfo.Fields.state
//                ,TsRoadShowInfo.Fields.deltag
//                ,TsRoadShowInfo.Fields.showDate
//                ,TsRoadShowInfo.Fields.viewUsers
//        ));
//        if(null == roadShowInfo || roadShowInfo.getDeltag() == true
//                || !CommonUtil.isEqual(roadShowInfo.getState(), CommonEnumContainer.CustomerAuditState.通过审核.getValue())){
//            return Result.miss(CommonEnumContainer.ResultStatus.查询不存在,"路演不存在");
//        }
//        TbUserRoadShowReservation has = userRoadShowReservationService.selectOne(
//                new QueryWrapper<TbUserRoadShowReservation>()
//                        .select(TsUserRoadShowReservation.Fields.id,TsUserRoadShowReservation.Fields.deltag)
//                        .eq(TsUserRoadShowReservation.Fields.userId,req.getUserId())
//                        .eq(TsUserRoadShowReservation.Fields.roadShowId,req.getRoadShowId())
//        );
//        if(has == null){
//            TbUserRoadShowReservation res = new TbUserRoadShowReservation()
//                    .setRoadShowId(req.getRoadShowId())
//                    .setUserId(req.getUserId())
//                    ;
//            userRoadShowReservationService.insert(new TqUserRoadShowReservation().setEntity(res));
//        }else{
//            userRoadShowReservationService.updateSelectItem(new UpdateWrapper<TbUserRoadShowReservation>()
//                    .update(
//                            Elements.valueOf(TsUserRoadShowReservation.Fields.updateDate,new Date()),
//                            Elements.valueOf(TsUserRoadShowReservation.Fields.deltag, CommonEnumContainer.Deltag.正常.getValue())
//                    )
//                    .eq(TsUserRoadShowReservation.Fields.id,has.getId())
//            );
//        }
//        if(has == null || has.getDeltag() == true){
//            //增加查看按人数
//            roadShowInfoService.updateSelectItem(
//                new UpdateWrapper<TbRoadShowInfo>()
//                        .update(
//                                Elements.valueOf(TsRoadShowInfo.Fields.updateDate,new Date()),
//                                Elements.valueOf(TsRoadShowInfo.Fields.viewUsers, roadShowInfo.getViewUsers() + 1)
//                        )
//                        .eq(TsRoadShowInfo.Fields.id,req.getRoadShowId())
//            );
//        }
//
//        return Result.success(Boolean.TRUE);
//    }
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "取消预约路演")
//    @PostMapping("roadShow/remove")
//    public Result<Boolean> removeRoadShow(@RequestBody TqReservationRoadShowOpera req){
//        TbRoadShowInfo roadShowInfo = roadShowInfoService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getRoadShowId()
//                , TsRoadShowInfo.Fields.id
//                ,TsRoadShowInfo.Fields.state
//                ,TsRoadShowInfo.Fields.deltag
//                ,TsRoadShowInfo.Fields.viewUsers
//                ,TsRoadShowInfo.Fields.showDate
//        ));
//        if(null == roadShowInfo || roadShowInfo.getDeltag() == true
//                || !CommonUtil.isEqual(roadShowInfo.getState(), CommonEnumContainer.CustomerAuditState.通过审核.getValue())){
//            return Result.miss(CommonEnumContainer.ResultStatus.查询不存在,"路演不存在");
//        }
//        if(roadShowInfo.getShowDate().compareTo(new Date()) <= 0){
//            //已经过了预约时间
//            return Result.miss(CommonEnumContainer.ResultStatus.数据状态不正确,"路演时间已过，无法取消预约");
//        }
//        TbUserRoadShowReservation has = userRoadShowReservationService.selectOne(
//                new QueryWrapper<TbUserRoadShowReservation>()
//                        .select(TsUserRoadShowReservation.Fields.id,TsUserRoadShowReservation.Fields.deltag)
//                        .eq(TsUserRoadShowReservation.Fields.userId,req.getUserId())
//                        .eq(TsUserRoadShowReservation.Fields.roadShowId,req.getRoadShowId())
//        );
//        if(has != null && has.getDeltag() == false){
//            userRoadShowReservationService.updateSelectItem(new UpdateWrapper<TbUserRoadShowReservation>()
//                    .update(
//                            Elements.valueOf(TsUserRoadShowReservation.Fields.updateDate,new Date()),
//                            Elements.valueOf(TsUserRoadShowReservation.Fields.deltag, CommonEnumContainer.Deltag.已删除.getValue())
//                    )
//                    .eq(TsUserRoadShowReservation.Fields.id,has.getId()))
//            ;
//            //减少查看按人数
//            roadShowInfoService.updateSelectItem(
//                    new UpdateWrapper<TbRoadShowInfo>()
//                            .update(
//                                    Elements.valueOf(TsRoadShowInfo.Fields.updateDate,new Date()),
//                                    Elements.valueOf(TsRoadShowInfo.Fields.viewUsers, roadShowInfo.getViewUsers() - 1)
//                            )
//                            .eq(TsRoadShowInfo.Fields.id,req.getRoadShowId())
//            );
//        }
//        return Result.success(Boolean.TRUE);
//    }

//
//    //region 机构
//
//
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "预约的机构列表")
//    @GetMapping("org/list")
//    public Result<TvPageList<TvUserOrgReservation>> orgList(TqReservationOrgList req){
//        TsUserOrgReservation query = new TsUserOrgReservation();
//        query.setEntity(new TbUserOrgReservation());
//        query.getEntity().setUserId(req.getUserId());
//        query.getEntity().setState(req.getState());
//        query.setPageInfo(req.getPageInfo());
//        PageInfo pageInfo = query.getPageInfo();
//        if(null == query.getPageInfo()){
//            pageInfo = new PageInfo();
//        }
//        query.setPageInfo(pageInfo);
//        if(CommonUtil.isNormalSql(query.getPageInfo().getSortField())){
//            query.orderBy(OrderBy.valueOf(Order.valueOf(req.getPageInfo().getSortDistanct().toUpperCase()), SelectAlias.valueOf(query.getPageInfo().getSortField())));
//        }
//
//        TvPageList<TvUserOrgReservation> pageList = new TvPageList<>();
//        pageInfo.setRecordCount(userOrgReservationService.selectCount(query));
//        pageList.setPageInfo(pageInfo);
//        pageList.setList(userOrgReservationService.selectPriOrg(query));
//        return Result.success(pageList);
//    }
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "预约机构")
//    @PostMapping("org/add")
//    public Result<Boolean> addOrgs(@RequestBody TqReservationOrgOpera req){
//        checkOrgExists(req.getPartyId());
//        TbUserOrgReservation has = userOrgReservationService.selectOne(
//                new QueryWrapper<TbUserOrgReservation>()
//                        .select("id,deltag")
//                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
//                        .where(FieldValue.fromCondition("partyId.eq",req.getPartyId()))
//        );
//        if(has == null){
//            TbUserOrgReservation reservation = new TbUserOrgReservation()
//                    .setPartyId(req.getPartyId())
//                    .setUserId(req.getUserId())
//                    .setState(CommonEnumContainer.ReservationDealStatus.未处理.getValue())
//                    ;
//            userOrgReservationService.insert(new TqUserOrgReservation().setEntity(reservation));
//        }else{
//            has.setUpdateDate(new Date());
//            has.setDeltag(CommonEnumContainer.Deltag.正常.getValue());
//            userOrgReservationService.updateSelectiveByPrimaryKey(has);
//        }
//        return Result.success(Boolean.TRUE);
//    }
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "取消预约机构")
//    @PostMapping("org/remove")
//    public Result<Boolean> removeOrg(@RequestBody TqReservationOrgOpera req){
//        checkOrgExists(req.getPartyId());
//        TbUserOrgReservation has = userOrgReservationService.selectOne(
//                new QueryWrapper<TbUserOrgReservation>()
//                        .select("id,deltag")
//                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
//                        .where(FieldValue.fromCondition("partyId.eq",req.getPartyId()))
//        );
//        if(has != null && CommonUtil.isEqual(CommonEnumContainer.ReservationDealStatus.未处理.getValue(),has.getState())){
//            has.setUpdateDate(new Date());
//            has.setDeltag(CommonEnumContainer.Deltag.已删除.getValue());
//            userOrgReservationService.updateSelectiveByPrimaryKey(has);
//        }
//        return Result.success(Boolean.TRUE);
//    }
//
//    private void checkOrgExists(Long partyId){
//        if(mdInstitutionService.selectCount(new QueryWrapper<TbMdInstitution>()
//                .where(FieldValue.fromCondition("partyId.eq",partyId))
//        ) == 0){
//            throw new NoExistsException("机构不存在");
//        }
//    }
//
//
//
//    //endregion
//
//    //region 机构人员
//
//
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "预约的产品经理")
//    @GetMapping("manager/list")
//    public Result<TvPageList<TvUserPeopleReservation>> managerList(TqReservationPeopleList req){
//        TsUserPeopleReservation query = new TsUserPeopleReservation();
//        query.setEntity(new TbUserPeopleReservation());
//        query.getEntity().setUserId(req.getUserId());
//        query.getEntity().setState(req.getState());
//        query.setPageInfo(req.getPageInfo());
//        PageInfo pageInfo = query.getPageInfo();
//        if(null == query.getPageInfo()){
//            pageInfo = new PageInfo();
//        }
//        query.setPageInfo(pageInfo);
//        if(CommonUtil.isNormalSql(query.getPageInfo().getSortField())){
//            query.orderBy(OrderBy.valueOf(Order.valueOf(req.getPageInfo().getSortDistanct().toUpperCase()), SelectAlias.valueOf(query.getPageInfo().getSortField())));
//        }
//
//        TvPageList<TvUserPeopleReservation> pageList = new TvPageList<>();
//        pageInfo.setRecordCount(userPeopleReservationService.selectCount(query));
//        pageList.setPageInfo(pageInfo);
//
//        pageList.setList(userPeopleReservationService.selectPriManager(query));
//        return Result.success(pageList);
//    }
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "预约经理")
//    @PostMapping("manager/add")
//    public Result<Boolean> addManager(@RequestBody TqReservationPeopleOpera req){
//        long reservationUserId = checkManagerExists(req.getPersonId());
//        TbUserPeopleReservation has = userPeopleReservationService.selectOne(
//                new QueryWrapper<TbUserPeopleReservation>()
//                        .select("id,deltag")
//                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
//                        .where(FieldValue.fromCondition("reservationUserId.eq",reservationUserId))
//        );
//        if(has == null){
//            TbUserPeopleReservation reservation = new TbUserPeopleReservation()
//                    .setReservationUserId(reservationUserId)
//                    .setUserId(req.getUserId())
//                    .setState(CommonEnumContainer.ReservationDealStatus.未处理.getValue())
//                    ;
//            userPeopleReservationService.insert(new TqUserPeopleReservation().setEntity(reservation));
//        }else{
//            has.setUpdateDate(new Date());
//            has.setDeltag(CommonEnumContainer.Deltag.正常.getValue());
//            userPeopleReservationService.updateSelectiveByPrimaryKey(has);
//        }
//        return Result.success(Boolean.TRUE);
//    }
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "取消预约经理")
//    @PostMapping("manager/remove")
//    public Result<Boolean> removeManager(@RequestBody TqReservationPeopleOpera req){
//        long reservationUserId = checkManagerExists(req.getPersonId());
//        TbUserPeopleReservation has = userPeopleReservationService.selectOne(
//                new QueryWrapper<TbUserPeopleReservation>()
//                        .select("id,deltag")
//                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
//                        .where(FieldValue.fromCondition("reservationUserId.eq",reservationUserId))
//        );
//        if(has != null && CommonUtil.isEqual(CommonEnumContainer.ReservationDealStatus.未处理.getValue(),has.getState())){
//            has.setUpdateDate(new Date());
//            has.setDeltag(CommonEnumContainer.Deltag.已删除.getValue());
//            userPeopleReservationService.updateSelectiveByPrimaryKey(has);
//        }
//        return Result.success(Boolean.TRUE);
//    }
//
//    private long checkManagerExists(Long personId){
//        TbPeopleManage manage = peopleManageService.selectOne(
//                new QueryWrapper<TbPeopleManage>().select("userId")
//                        .where(FieldValue.fromCondition("personId.eq",personId))
//                        .where(FieldValue.fromCondition("deltag.eq", CommonEnumContainer.Deltag.正常.getValue()))
//                        .where(FieldValue.fromCondition("visible.neq",false))
//        );
//        if(null == manage){
//            throw new NoExistsException("基金经理不存在");
//        }
//        return manage.getUserId();
//    }
//
//
//    //endregion
//
//    //region 基金
//
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "预约的产品列表")
//    @GetMapping("funds/list")
//    public Result<TvPageList<TvUserProReservation>> fundsList(TqReservationProList req){
//        TsUserProReservation query = new TsUserProReservation();
//        query.setEntity(new TbUserProReservation());
//        query.getEntity().setUserId(req.getUserId());
//        query.getEntity().setType(req.getType());
//        query.getEntity().setState(req.getState());
//        query.setPageInfo(req.getPageInfo());
//        PageInfo pageInfo = query.getPageInfo();
//        if(null == query.getPageInfo()){
//            pageInfo = new PageInfo();
//        }
//        query.setPageInfo(pageInfo);
//        if(CommonUtil.isNormalSql(query.getPageInfo().getSortField())){
//            query.orderBy(OrderBy.valueOf(Order.valueOf(req.getPageInfo().getSortDistanct().toUpperCase()), SelectAlias.valueOf(query.getPageInfo().getSortField())));
//        }
//
//        TvPageList<TvUserProReservation> pageList = new TvPageList<>();
//        pageInfo.setRecordCount(userProReservationService.selectCount(query));
//        pageList.setPageInfo(pageInfo);
//
//        if(req.getType() == CommonEnumContainer.ProductType.私募.getValue()){
//            pageList.setList(userProReservationService.selectPriFunds(query));
//        }
//        return Result.success(pageList);
//    }
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "预约产品")
//    @PostMapping("funds/add")
//    public Result<Boolean> addFunds(@RequestBody TqReservationProOpera req){
//        checkProExists(req.getSecurityId());
//        TbUserProReservation has = userProReservationService.selectOne(
//                new QueryWrapper<TbUserProReservation>()
//                        .select("id,deltag,type")
//                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
//                        .where(FieldValue.fromCondition("securityId.eq",req.getSecurityId()))
//        );
//        if(has == null){
//            TbUserProReservation reservation = new TbUserProReservation()
//                    .setSecurityId(req.getSecurityId())
//                    .setUserId(req.getUserId())
//                    .setType(req.getType())
//                    .setState(CommonEnumContainer.ReservationDealStatus.未处理.getValue())
//                    ;
//            userProReservationService.insert(new TqUserProReservation().setEntity(reservation));
//        }else{
//            has.setUpdateDate(new Date());
//            has.setDeltag(CommonEnumContainer.Deltag.正常.getValue());
//            userProReservationService.updateSelectiveByPrimaryKey(has);
//        }
//        return Result.success(Boolean.TRUE);
//    }
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "取消预约产品")
//    @PostMapping("funds/remove")
//    public Result<Boolean> removeFunds(@RequestBody TqReservationProOpera req){
//        checkProExists(req.getSecurityId());
//        TbUserProReservation has = userProReservationService.selectOne(
//                new QueryWrapper<TbUserProReservation>()
//                        .select("id,deltag,type")
//                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
//                        .where(FieldValue.fromCondition("securityId.eq",req.getSecurityId()))
//        );
//        if(has != null && CommonUtil.isEqual(CommonEnumContainer.ReservationDealStatus.未处理.getValue(),has.getState())){
//            has.setUpdateDate(new Date());
//            has.setDeltag(CommonEnumContainer.Deltag.已删除.getValue());
//            userProReservationService.updateSelectiveByPrimaryKey(has);
//        }
//        return Result.success(Boolean.TRUE);
//    }
//
//    private void checkProExists(Long securityId){
//        if(mdSecurityService.selectCount(new QueryWrapper<TbMdSecurity>()
//                .where(FieldValue.fromCondition("securityId.eq",securityId))
//        ) == 0){
//            throw new NoExistsException("产品不存在");
//        }
//    }
//
//    //endregion


}

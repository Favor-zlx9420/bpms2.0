package com.rong.assembly.api.controller.usercenter;

import com.rong.assembly.api.module.request.uc.reservation.TqReservationList;
import com.rong.assembly.api.module.request.uc.reservation.TqUpdateReservation;
import com.rong.assembly.api.module.request.uc.store.TqStoreServiceInfoList;
import com.rong.assembly.api.module.request.uc.store.TqStoreSubmitServiceInfo;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbUserReservation;
import com.rong.member.module.query.TsMemBase;
import com.rong.member.module.view.TvUserReservation;
import com.rong.member.service.MemBaseService;
import com.rong.member.service.UserReservationService;
import com.rong.store.module.entity.TbDirectStoreServiceHistory;
import com.rong.store.module.entity.TbDirectStoreUser;
import com.rong.store.module.query.TsDirectStoreServiceHistory;
import com.rong.store.module.query.TsDirectStoreUser;
import com.rong.store.module.request.TqDirectStoreServiceHistory;
import com.rong.store.module.view.TvDirectStoreServiceHistory;
import com.rong.store.service.DirectStoreServiceHistoryService;
import com.rong.store.service.DirectStoreUserService;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import com.vitily.mybatis.core.entity.FieldValue;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.IdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.ClassAssociateTableInfo;
import com.vitily.mybatis.util.CompareAlias;
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

@Api(tags = "服务中心")
@RestController
@RequestMapping("user/customer")
public class CustomerCenterController {
    @Autowired
    private MemBaseService memBaseService;
    @Autowired
    private UserReservationService userReservationService;

    @Autowired
    private DirectStoreServiceHistoryService directStoreServiceHistoryService;
    @Autowired
    private DirectStoreUserService directStoreUserService;

    // 用户预约列表
    @ApiOperation(value = "用户预约列表")
    @GetMapping("reservation/list")
    public Result<TvPageList<TvUserReservation>> orgList(TqReservationList req){
        TbMemBase ut = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getUserId(),
                TsMemBase.Fields.type
        ));
        if(!CommonUtil.isEqual(ut.getType(), MemEnumContainer.MemType.直营店客服.getValue())){
            return Result.success(new TvPageList<>());
        }
        PageInfo pageInfo = req.getPageInfo();
        if(null == pageInfo){
            pageInfo = new PageInfo();
        }
        MultiTableQueryWrapper queryWrapper = userReservationService.getMultiCommonWrapper()
                .selectAllFiels(true)
                .select("mi.partyShortName reservationOrgInfo,ms.secShortName reservationProInfo,mp.name reservationManageInfo")
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), x->
                        x.eqc(CompareAlias.valueOf("mi.partyId"),CompareAlias.valueOf("e.targetId"))
                        .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.ReservationType.ORGANIZATION.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdSecurity.class,"ms"), x->
                        x.eqc(CompareAlias.valueOf("ms.securityId"),CompareAlias.valueOf("e.targetId"))
                                .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.ReservationType.PRODUCT.getValue())
                )
                .leftJoin(ClassAssociateTableInfo.valueOf(TbMdPeople.class,"mp"), x->
                        x.eqc(CompareAlias.valueOf("mp.personId"),CompareAlias.valueOf("e.targetId"))
                                .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.ReservationType.FUND_MANAGER.getValue())
                )
                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")))
                .page(pageInfo)
                ;
        if(null !=req.getDealStatus()){
            queryWrapper.where(FieldValue.fromCondition("e.dealStatus.eq",req.getDealStatus()));
        }
        if(null !=req.getType()){
            queryWrapper.where(FieldValue.fromCondition("e.type.eq",req.getType()));
        }
        TvPageList<TvUserReservation> pageList = userReservationService.selectPageList(queryWrapper);
        for(TvUserReservation v:pageList.getList()){
            if(CommonUtil.isEqual(v.getType(), CommonEnumContainer.ReservationType.ORGANIZATION.getValue())){
                v.setInfo(v.getReservationOrgInfo());
            }else if(CommonUtil.isEqual(v.getType(), CommonEnumContainer.ReservationType.PRODUCT.getValue())){
                v.setInfo(v.getReservationProInfo());
            }else if(CommonUtil.isEqual(v.getType(), CommonEnumContainer.ReservationType.FUND_MANAGER.getValue())){
                v.setInfo(v.getReservationManageInfo());
            }
        }
        return Result.success(pageList);
    }
    //更新预约状态

    /**
     * 更新预约状态为已处理
     * @param req
     * @return
     */
    @ApiOperation(value = "更新预约状态为已处理")
    @PostMapping("reservation/updateState")
    public Result<Boolean> updateReservationState(@RequestBody TqUpdateReservation req){
        TbMemBase ut = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getUserId(),
                TsMemBase.Fields.type
        ));
        if(!CommonUtil.isEqual(ut.getType(), MemEnumContainer.MemType.直营店客服.getValue())){
            return Result.success();
        }
        TbUserReservation has = userReservationService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getId()));
        if(null == has){
            throw new NoExistsException("预约项目不存在");
        }
        has.setUpdateDate(new Date());
        has.setDealStatus(CommonEnumContainer.ReservationDealStatus.PROCESSED.getValue());
        has.setDualUserId(req.getUserId());
        userReservationService.updateSelectiveByPrimaryKey(has);
        return Result.success(Boolean.TRUE);
    }

    /**
     * 提交服务内容,只有直营店客服、入驻经理、官方客服才可以
     * @param req
     * @return
     */
    @ApiOperation(value = "提交服务内容,只有直营店客服、入驻经理才可以")
    @PostMapping("service/submitService")
    public Result<Boolean> submitService(@RequestBody TqStoreSubmitServiceInfo req){
        TbMemBase user = memBaseService.selectOne(
          WrapperFactory.queryWrapper()
                  .select(TsMemBase.Fields.id, TsMemBase.Fields.type)
                  .eq(TsMemBase.Fields.id,req.getUserId())
        );
        Long partyId = null;
        if(CommonUtil.isEqual(user.getType(), MemEnumContainer.MemType.直营店客服.getValue())) {
            TbDirectStoreUser server = directStoreUserService.selectOne(
                    new QueryWrapper()
                            .eq(TsDirectStoreUser.Fields.userId, req.getUserId())
                            //.eq(TsDirectStoreUser.Fields.type, CommonEnumContainer.StoreUserType.客服.getValue())
                            .eq(TsDirectStoreUser.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
            );
            if(null != server){
                partyId = server.getPartyId();
            }
        }

        TbDirectStoreServiceHistory serviceInfo = directStoreServiceHistoryService.selectOne(
                WrapperFactory.queryWrapper()
                        .eq(TsDirectStoreServiceHistory.Fields.customerUserId,req.getUserId())
                        .eq(TsDirectStoreServiceHistory.Fields.investorUserId,req.getInvestorUserId())
                        .ge(TsDirectStoreServiceHistory.Fields.createDate, DateUtil.getCurDateTime(DateUtil.yyyy_MM_dd_EN))
        );
        //一天最多一人次
        if(null != serviceInfo){
            return Result.success();
        }
        serviceInfo = new TbDirectStoreServiceHistory();
        serviceInfo.setAuditResult(CommonEnumContainer.CustomerServiceAuditResult.TO_AUDIT.getValue());
        serviceInfo.setCustomerUserId(req.getUserId());
        serviceInfo.setPartyId(partyId);
        serviceInfo.setInvestorUserId(req.getInvestorUserId());
        serviceInfo.setContent(req.getContent());
        serviceInfo.setLinkUrl(req.getLinkUrl());
        serviceInfo.setPicUrl(req.getPicUrl());
        serviceInfo.setType(req.getType());
        directStoreServiceHistoryService.insert(new TqDirectStoreServiceHistory().setEntity(serviceInfo));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 我的服务记录
     * @param req
     * @return
     */
    @ApiOperation(value = "我的服务记录")
    @GetMapping("service/list")
    public Result<TvPageList<TvDirectStoreServiceHistory>> myServiceList(TqStoreServiceInfoList req){
        TbDirectStoreUser server = directStoreUserService.selectOne(
                new QueryWrapper()
                        .eq(TsDirectStoreUser.Fields.userId,req.getUserId())
                        //.eq(TsDirectStoreUser.Fields.type, CommonEnumContainer.StoreUserType.客服.getValue())
                        .eq(TsDirectStoreUser.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        if(null == server){
            return Result.success();
        }
        if(null == req.getPageInfo()){
            req.setPageInfo(new PageInfo());
        }
        MultiTableQueryWrapper queryWrapper = directStoreServiceHistoryService.getMultiCommonWrapper()
                .eq(CompareAlias.valueOf(TsDirectStoreServiceHistory.Fields.customerUserId,"e"),req.getUserId())
                .eq(CompareAlias.valueOf(TsDirectStoreServiceHistory.Fields.partyId,"e"),server.getPartyId())
                .page(req.getPageInfo())
                ;
        if(null != req.getAuditResult()){
            queryWrapper.eq(CompareAlias.valueOf(TsDirectStoreServiceHistory.Fields.auditResult,"e"),req.getAuditResult());
        }
        TvPageList<TvDirectStoreServiceHistory> pageList = directStoreServiceHistoryService.selectPageList(queryWrapper);
        for(TvDirectStoreServiceHistory s:pageList.getList()){
            s.setCustomerUserName(StringUtil.markLastName(s.getCustomerUserName()));
            s.setCustomerUserRealName(StringUtil.markLastName(s.getCustomerUserRealName()));
            s.setInvestorUserName(StringUtil.markLastName(s.getInvestorUserName()));
            s.setInvestorUserRealName(StringUtil.markLastName(s.getInvestorUserRealName()));
        }
        return Result.success(pageList);
    }




//
//    //region 机构
//
//    /**
//     *
//     *
//     * @param req
//     * @return
//     */
//    @ApiOperation(value = "预约机构的用户列表，持有机构代理的用户才能处理此项")
//    @GetMapping("org/list")
//    public Result<TvPageList<TrReservationUser>> orgList(TqReservationUserListOfOrg req){
//        TbMemBase ut = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getUserId(),
//                TsMemBase.Fields.type
//        ));
//        if(!CommonUtil.isEqual(ut.getType(), MemEnumContainer.MemType.机构代理.getValue())){
//            return Result.success(new TvPageList<>());
//        }
//        PageInfo pageInfo = req.getPageInfo();
//        if(null == pageInfo){
//            pageInfo = new PageInfo();
//        }
//        MultiTableQueryWrapper<TbUserOrgReservation> queryWrapper = userOrgReservationService.getMultiCommonWrapper()
//                .selectAllFiels(true)
//                .select("m.userName,m.realName")
//                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"m"),x->
//                        x.eqc(CompareAlias.valueOf("m.id"),CompareAlias.valueOf("e.userId"))
//                )
//                .leftJoin(ClassAssociateTableInfo.valueOf(TbOrgProxy.class,"op"),y->
//                        y
//                                .eq(CompareAlias.valueOf("op.userId"),req.getUserId())
//                                .eq(CompareAlias.valueOf("op.partyId"),req.getPartyId())
//                        )
//                .where(FieldValue.fromCondition("e.partyId.eq",req.getPartyId()))
//                .where(FieldValue.fromCondition("op.partyId.eq",req.getPartyId()))
//                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
//                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")))
//                .page(pageInfo)
//                ;
//        if(null !=req.getState()){
//            queryWrapper.where(FieldValue.fromCondition("e.state.eq",req.getState()));
//        }
//        TvPageList<TvUserOrgReservation> pageList = userOrgReservationService.selectPageList(queryWrapper);
//        TvPageList<TrReservationUser> outList = new TvPageList<>();
//        outList.setPageInfo(pageList.getPageInfo());
//        outList.setList(new ArrayList<>());
//        for(TvUserOrgReservation user:pageList.getList()){
//            TrReservationUser ru = new TrReservationUser();
//            ru.setUserId(user.getUserId());
//            ru.setRealName(StringUtil.markLastName(user.getRealName()));
//            ru.setUserName(StringUtil.markCenterPhone(user.getUserName()));
//            ru.setState(user.getState());
//            ru.setReservationDate(user.getCreateDate());
//            outList.getList().add(ru);
//        }
//        return Result.success(outList);
//    }
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
//    @ApiOperation(value = "预约基金经理的用户列表，只有基金经理本人才能处理此项")
//    @GetMapping("manager/list")
//    public Result<TvPageList<TrReservationUser>> managerList(TqReservationUserListOfManager req){
//        TbMemBase ut = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getUserId(),
//                TsMemBase.Fields.type
//        ));
//        if(!CommonUtil.isEqual(ut.getType(), MemEnumContainer.MemType.基金经理.getValue())){
//            return Result.success(new TvPageList<>());
//        }
//
//        TbPeopleManage manage = peopleManageService.selectOne(
//                new QueryWrapper<TbPeopleManage>().select("userId")
//                        .where(FieldValue.fromCondition("userId.eq",req.getUserId()))
//                        .where(FieldValue.fromCondition("deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
//                        .where(FieldValue.fromCondition("visible.neq",false))
//        );
//        if(null == manage){
//            return Result.success(new TvPageList<>());
//        }
//        PageInfo pageInfo = req.getPageInfo();
//        if(null == pageInfo){
//            pageInfo = new PageInfo();
//        }
//        MultiTableQueryWrapper<TbUserPeopleReservation> queryWrapper = userPeopleReservationService.getMultiCommonWrapper()
//                .selectAllFiels(true)
//                .select("m.userName,m.realName")
//                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"m"),x->
//                        x.eqc(CompareAlias.valueOf("m.id"),CompareAlias.valueOf("e.userId"))
//                )
//                .where(FieldValue.fromCondition("e.reservationUserId.eq",req.getUserId()))
//                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
//                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")))
//                .page(pageInfo)
//                ;
//        if(null !=req.getState()){
//            queryWrapper.where(FieldValue.fromCondition("e.state.eq",req.getState()));
//        }
//        TvPageList<TvUserPeopleReservation> pageList = userPeopleReservationService.selectPageList(queryWrapper);
//        TvPageList<TrReservationUser> outList = new TvPageList<>();
//        outList.setPageInfo(pageList.getPageInfo());
//        outList.setList(new ArrayList<>());
//        for(TvUserPeopleReservation u:pageList.getList()){
//            TrReservationUser ru = new TrReservationUser();
//            ru.setUserId(u.getUserId());
//            ru.setRealName(StringUtil.markLastName(u.getRealName()));
//            ru.setUserName(StringUtil.markCenterPhone(u.getUserName()));
//            ru.setReservationDate(u.getCreateDate());
//            ru.setState(u.getState());
//            outList.getList().add(ru);
//        }
//        return Result.success(outList);
//    }
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
//    @ApiOperation(value = "预约产品的用户列表，只有机构代理才能处理此项")
//    @GetMapping("funds/list")
//    public Result<TvPageList<TrReservationUser>> fundsList(TqReservationUserListOfFunds req){
//        TbMemBase user = memBaseService.selectItemByPrimaryKey(IdWrapper.valueOf(req.getUserId(),
//                TsMemBase.Fields.type
//        ));
//        if(!CommonUtil.isEqual(user.getType(), MemEnumContainer.MemType.机构代理.getValue())){
//            return Result.success(new TvPageList<>());
//        }
//        PageInfo pageInfo = req.getPageInfo();
//        if(null == pageInfo){
//            pageInfo = new PageInfo();
//        }
//        MultiTableQueryWrapper<TbUserProReservation> queryWrapper = userProReservationService.getMultiCommonWrapper()
//                .selectAllFiels(true)
//                .select("m.userName,m.realName")
//                .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"m"),x->
//                        x.eqc(CompareAlias.valueOf("m.id"),CompareAlias.valueOf("e.userId"))
//                        )
//                .where(FieldValue.fromCondition("e.securityId.eq",req.getSecurityId()))
//                .where(FieldValue.fromCondition("e.deltag.eq", CommonEnumContainer.Deltag.NORMAL.getValue()))
//                .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("e.id")))
//                .page(pageInfo)
//                ;
//        if(null !=req.getState()){
//            queryWrapper.where(FieldValue.fromCondition("e.state.eq",req.getState()));
//        }
//        TvPageList<TvUserProReservation> pageList = userProReservationService.selectPageList(queryWrapper);
//        TvPageList<TrReservationUser> outList = new TvPageList<>();
//        outList.setPageInfo(pageList.getPageInfo());
//        outList.setList(new ArrayList<>());
//        for(TvUserProReservation u:pageList.getList()){
//            TrReservationUser ru = new TrReservationUser();
//            ru.setUserId(u.getUserId());
//            ru.setRealName(StringUtil.markLastName(u.getRealName()));
//            ru.setUserName(StringUtil.markCenterPhone(u.getUserName()));
//            ru.setReservationDate(u.getCreateDate());
//            ru.setState(u.getState());
//            outList.getList().add(ru);
//        }
//        return Result.success(outList);
//    }
//    //endregion

}

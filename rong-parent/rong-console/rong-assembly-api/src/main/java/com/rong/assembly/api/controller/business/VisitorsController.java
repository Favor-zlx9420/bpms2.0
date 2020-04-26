package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.mapper.RespPeopleInfoMapper;
import com.rong.assembly.api.module.request.TqUserReserve;
import com.rong.assembly.api.module.request.buz.TqCustomerUser;
import com.rong.assembly.api.module.request.uc.TqSubmitFeedback;
import com.rong.assembly.api.module.response.TrCustomerServer;
import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.module.Result;
import com.rong.common.module.TqBase;
import com.rong.common.module.UserInfo;
import com.rong.common.util.WrapperFactory;
import com.rong.member.consts.MemEnumContainer;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbUserReservation;
import com.rong.member.module.query.TsMemCustomer;
import com.rong.member.module.query.TsUserReservation;
import com.rong.member.module.request.TqUserReservation;
import com.rong.member.service.UserReservationService;
import com.rong.member.util.MemberUtil;
import com.rong.store.module.entity.TbDirectStoreUser;
import com.rong.store.module.query.TsDirectStoreUser;
import com.rong.store.module.view.TvDirectStoreUser;
import com.rong.store.service.DirectStoreUserService;
import com.rong.sys.module.entity.TbLabel;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import com.rong.user.module.entity.TbUserFeedBack;
import com.rong.user.module.entity.TbUserLabel;
import com.rong.user.module.request.TqUserFeedBack;
import com.rong.user.service.UserFeedBackService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.PageInfo;
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

import java.util.List;

/**
 * 访客、客服中心
 */
@Api(tags = "访客、客服中心")
@RestController
@RequestMapping("customer")
public class VisitorsController {
    @Autowired
    private UserReservationService userReservationService;
    @Autowired
    private CommonServiceCache commonServiceCache;
    @Autowired
    private DirectStoreUserService directStoreUserService;
    @Autowired
    private RespPeopleInfoMapper respPeopleInfoMapper;
    @Autowired
    private UserFeedBackService userFeedBackService;
    /**
     * 预约节目
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "预约节目")
    @PostMapping("reserve")
    public Result reserve(@RequestBody TqUserReserve req){

        TbUserReservation customer = userReservationService.selectOne(
                new QueryWrapper()
                        .eq(TsUserReservation.Fields.phone,req.getPhone())
                        .eq(TsUserReservation.Fields.dealStatus, CommonEnumContainer.ReservationDealStatus.UNTREATED.getValue())
                        .eq(TsUserReservation.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
                        .eq(TsUserReservation.Fields.targetId, req.getTargetId())
                        .select(TsMemCustomer.Fields.id)
        );
        if(customer != null){
            throw new DuplicateDataException("您已重复预约，请等待处理");
        }
        customer = new TbUserReservation();
        customer.setName(req.getName())
                .setPhone(req.getPhone())
                .setDealStatus( CommonEnumContainer.ReservationDealStatus.UNTREATED.getValue())
                .setTargetId(req.getTargetId())
                .setType(req.getType())
        ;
        CommonServiceCache memCache = commonServiceCache.getInstance(DictionaryKey.Keys.MEMBER_OF_THE_TOKEN, CacheSerializableDelegate.jsonSeriaze());
        UserInfo userInfo = memCache.getFromServer(req.getUserAuthToken(), UserInfo.class);
        if(null != userInfo){
            customer.setReservationUserId(userInfo.getUserId());
        }

        return Result.success(userReservationService.insert(new TqUserReservation().setEntity(customer)));
    }

    /**
     * 直营店客服列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "直营店客服列表")
    @GetMapping("service/users")
    public Result<List<TvDirectStoreUser>> serviceUsers(TqCustomerUser req){
        List<TvDirectStoreUser> storeUsers = directStoreUserService.selectViewList(directStoreUserService.getMultiCommonWrapper()
                .eq(CompareAlias.valueOf(TsDirectStoreUser.Fields.partyId), req.getPartyId())
                .eq(CompareAlias.valueOf("e.type"), CommonEnumContainer.StoreUserType.SERVICE.getValue())
                .eq(CompareAlias.valueOf(TsDirectStoreUser.Fields.state, "e"), CommonEnumContainer.CustomerAuditState.GET_APPROVED.getValue())
                .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        for(TvDirectStoreUser m:storeUsers){
            m.setOnlineState(memCache.existsInServer(m.getUserType()+"-" + m.getUserId())?
                    CommonEnumContainer.OnlineState.ON_LINE.getValue(): CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
        }
        return Result.success(storeUsers);
    }

    /**
     * 官方客服列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "官方客服列表")
    @GetMapping("official/service/users")
    public Result<List<TrCustomerServer>> officialServiceUsers(TqBase req){
        List<TrCustomerServer> customerServers =
                respPeopleInfoMapper.selectCustomerUsers(WrapperFactory.multiQueryWrapper(TbMemBase.class)
                        .select("e.id userId,userName,realName,nickName,headPortrait,e.type")
                        .select("ul.labelId,l.name labelName,ul.recommend,ul.sort,ul.labelReason,ul.labelVar0,ul.labelVar1,ul.labelVar2,ul.labelVar3")
                        .select0(
                                SelectAlias.valueOf("(select count(1)from tb_direct_store_service_history where customer_user_id=e.id and audit_result in(0,1)) serviceNumCount",true)
                                ,
                                SelectAlias.valueOf("(select count(DISTINCT investor_user_id) from tb_direct_store_service_history where customer_user_id=e.id and audit_result in(0,1)) serviceUserCount",true)
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbUserLabel.class,"ul"), ul->
                                ul.eqc(CompareAlias.valueOf("ul.userId"),CompareAlias.valueOf("e.id"))
                                )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbLabel.class,"l"), l->
                                l.eqc(CompareAlias.valueOf("l.id"),CompareAlias.valueOf("ul.labelId"))
                        )
                        .eq(CompareAlias.valueOf("e.type"), MemEnumContainer.MemType.官方客服.getValue())
                        .eq(CompareAlias.valueOf("e.state"), CommonEnumContainer.State.NORMAL.getValue())
                        .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
                );
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        for(TrCustomerServer customer:customerServers){
            customer.setOnlineState(memCache.existsInServer(customer.getType()+"-" + customer.getUserId())?
                    CommonEnumContainer.OnlineState.ON_LINE.getValue(): CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
        }
        return Result.success(customerServers);
    }

    /**
     * 服务之星
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "服务之星")
    @GetMapping("service/start")
    public Result<List<TvDirectStoreUser>> startServiceUsers(TqBase req){
        List<TvDirectStoreUser> storeUsers =
                directStoreUserService.selectViewList(WrapperFactory.multiQueryWrapper(TbDirectStoreUser.class)
                        .select("e.partyId,e.userId,mb.realName,mi.partyShortName,mi.partyFullName,mb.type userType")
                        .select0(
                                SelectAlias.valueOf("ifnull(e.nickname,mb.nick_name) nickName",true)
                                ,
                                SelectAlias.valueOf("ifnull(e.position,mb.position) position",true)
                                ,
                                SelectAlias.valueOf("ifnull(e.head_portrait,mb.head_portrait) headPortrait",true)
                                ,
                                SelectAlias.valueOf("ifnull(e.head_portrait,mb.head_portrait) headPortrait",true)
                                ,
                                SelectAlias.valueOf("(select person_id from tb_people_manage where user_id=e.user_id limit 1) personId",true)
                        )
                        .select0(
                                SelectAlias.valueOf("(select count(1)from tb_direct_store_service_history where customer_user_id=e.user_id and audit_result in(0,1)) serviceNumCount",true)
                                ,
                                SelectAlias.valueOf("(select count(DISTINCT investor_user_id) from tb_direct_store_service_history where customer_user_id=e.user_id and audit_result in(0,1)) serviceUserCount",true)
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbMemBase.class,"mb"), mb->
                            mb.eqc(CompareAlias.valueOf("mb.id"),CompareAlias.valueOf("e.userId"))
                        )
                        .leftJoin(ClassAssociateTableInfo.valueOf(TbMdInstitution.class,"mi"), mi->
                                mi.eqc(CompareAlias.valueOf("mi.partyId"),CompareAlias.valueOf("e.partyId"))
                        )
                        .eq(CompareAlias.valueOf("e.state"), CommonEnumContainer.State.NORMAL.getValue())
                        .eq(CompareAlias.valueOf("e.deltag"), CommonEnumContainer.Deltag.NORMAL.getValue())
                        .page(new PageInfo())
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("serviceNumCount",true)))
                        .orderBy(OrderBy.valueOf(Order.DESC,SelectAlias.valueOf("serviceUserCount",true)))
                );
        CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
        for(TvDirectStoreUser m:storeUsers){
            m.setOnlineState(memCache.existsInServer(m.getUserType()+"-" + m.getUserId())?
                    CommonEnumContainer.OnlineState.ON_LINE.getValue(): CommonEnumContainer.OnlineState.NOT_ONLINE.getValue());
        }
        return Result.success(storeUsers);
    }


    /**
     * 提交意见反馈（匿名）
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "提交意见反馈（匿名）")
    @PostMapping("feedback/submit")
    public Result<Boolean> feedbackSubmit(@RequestBody TqSubmitFeedback req){
        TbUserFeedBack feedBack = new TbUserFeedBack();
        feedBack.setContent(req.getContent());
        feedBack.setLink(req.getLink());
        feedBack.setResult(CommonEnumContainer.ReservationDealStatus.UNTREATED.getValue());
        feedBack.setTitle(req.getTitle());
        feedBack.setVisible(CommonEnumContainer.YesOrNo.RIGHT.getValue());
        feedBack.setSubmitUserId(req.getUserId());
        userFeedBackService.insert(new TqUserFeedBack().setEntity(feedBack));
        return Result.success(Boolean.TRUE);
    }

}

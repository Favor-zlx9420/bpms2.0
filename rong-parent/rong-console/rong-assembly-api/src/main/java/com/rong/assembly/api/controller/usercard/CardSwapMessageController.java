package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqDealRequestSwapCardInfo;
import com.rong.assembly.api.module.request.usercard.TqSendCardSwapInfo;
import com.rong.assembly.api.service.UserCardSwapService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.exception.DuplicateDataException;
import com.rong.common.module.Result;
import com.rong.common.util.Assert;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.EnumHelperUtil;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.entity.TbUserCardSwapMessage;
import com.rong.usercard.module.query.TsUserCardInfo;
import com.rong.usercard.module.query.TsUserCardSwapMessage;
import com.rong.usercard.module.request.TqUserCardSwapMessage;
import com.rong.usercard.service.UserCardInfoService;
import com.rong.usercard.service.UserCardSwapMessageService;
import com.vitily.mybatis.core.entity.Element;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import com.vitily.mybatis.util.Elements;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * 名片交换请求
 */
@Api(tags = "名片交换请求")
@RestController
@RequestMapping("usercard/swap")
public class CardSwapMessageController {
    @Autowired
    private UserCardSwapMessageService userCardSwapMessageService;
    @Autowired
    private UserCardInfoService userCardInfoService;
    @Autowired
    private UserCardSwapService userCardSwapService;

    /**
     * 发送交换请求（只有双方没有交换成功的才允许交换）
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "发送交换请求（只有双方没有交换成功的才允许交换）")
    @PostMapping("send")
    public Result<Integer> send(@RequestBody TqSendCardSwapInfo req) {
        TbUserCardInfo theCardInfo = userCardInfoService.selectOne(WrapperFactory.queryWrapper()
                .eq(TsUserCardInfo.Fields.id, req.getTheCardInfoId())
                .neq(TsUserCardInfo.Fields.userId, req.getUserId())
                .eq(TsUserCardInfo.Fields.visible, true)
        );
        Assert.notNull(theCardInfo, "要请求的名片不存在");

        TbUserCardInfo myCardInfo = userCardInfoService.selectOne(WrapperFactory.queryWrapper()
                .eq(TsUserCardInfo.Fields.id, req.getMyCardInfoId())
                .eq(TsUserCardInfo.Fields.userId, req.getUserId())
        );
        Assert.notNull(myCardInfo, "未创建名片");
        CommonEnumContainer.UserCardSwapState swapState = userCardSwapService.getBothSidesSwapState(req.getUserId(), theCardInfo.getUserId());
        if (!CommonUtil.inNumbers(
                swapState.getValue(),
                CommonEnumContainer.UserCardSwapState.NO_EXCHANGE_REQUEST.getValue(),
                CommonEnumContainer.UserCardSwapState.THE_EXCHANGE_REQUEST_HAS_BEEN_REFUSED.getValue(),
                CommonEnumContainer.UserCardSwapState.YOU_VOLUNTARILY_REFUSE_THE_EXCHANGE_REQUEST.getValue()
        )) {
            throw new DuplicateDataException(swapState.getDesc());
        }
        if (CommonEnumContainer.UserCardSwapState.THE_EXCHANGE_REQUEST_HAS_BEEN_REFUSED == swapState) {
            //继续申请
            ArrayList<Element> elements = new ArrayList<>();
            elements.add(Elements.valueOf(TsUserCardSwapMessage.Fields.updateDate, new Date()));
            CommonEnumContainer.DealResult dealResult;
            //如果对方无需认证即可交换，则直接把状态改成同意
            if (theCardInfo.getSwapVerify()) {
                dealResult = CommonEnumContainer.DealResult.UNTREATED;
            } else {
                dealResult = CommonEnumContainer.DealResult.HAS_AGREED_TO;
            }
            elements.add(Elements.valueOf(TsUserCardSwapMessage.Fields.dealResult, dealResult.getValue()));
            userCardSwapMessageService.updateSelectItem(
                    WrapperFactory.updateWrapper()
                            .update(elements)
                            .eq(TsUserCardSwapMessage.Fields.applicantUserId, req.getUserId())
                            .eq(TsUserCardSwapMessage.Fields.targetCardInfoId, req.getTheCardInfoId())
            );
            return Result.success(dealResult.getValue());
        } else if (CommonEnumContainer.UserCardSwapState.YOU_VOLUNTARILY_REFUSE_THE_EXCHANGE_REQUEST == swapState) {
            //自己主动拒绝，那么将申请人和被申请人的信息改过来
            CommonEnumContainer.DealResult dealResult;
            //如果对方无需认证即可交换，则直接把状态改成同意
            if (theCardInfo.getSwapVerify()) {
                dealResult = CommonEnumContainer.DealResult.UNTREATED;
            } else {
                dealResult = CommonEnumContainer.DealResult.HAS_AGREED_TO;
            }
            userCardSwapMessageService.updateSelectItem(
                    WrapperFactory.updateWrapper()
                            .update(
                                    Elements.valueOf(TsUserCardSwapMessage.Fields.updateDate, new Date()),
                                    Elements.valueOf(TsUserCardSwapMessage.Fields.dealResult, dealResult.getValue()),
                                    Elements.valueOf(TsUserCardSwapMessage.Fields.applicantCardInfoId, myCardInfo.getId()),
                                    Elements.valueOf(TsUserCardSwapMessage.Fields.applicantUserId, myCardInfo.getUserId()),
                                    Elements.valueOf(TsUserCardSwapMessage.Fields.targetCardInfoId, theCardInfo.getId()),
                                    Elements.valueOf(TsUserCardSwapMessage.Fields.targetUserId, theCardInfo.getUserId())
                            )
                            .eq(TsUserCardSwapMessage.Fields.applicantUserId, theCardInfo.getUserId())
                            .eq(TsUserCardSwapMessage.Fields.targetUserId, myCardInfo.getUserId())
            );
            return Result.success(dealResult.getValue());
        }
        TbUserCardSwapMessage userCardSwapMessage = new TbUserCardSwapMessage();
        userCardSwapMessage
                .setApplicantUserId(myCardInfo.getUserId())
                .setTargetUserId(theCardInfo.getUserId())
                .setApplicantCardInfoId(req.getMyCardInfoId())
                .setUpdateDate(new Date())
                .setTargetCardInfoId(req.getTheCardInfoId())
        ;
        //如果对方无需认证即可交换，则直接把状态改成同意
        if (theCardInfo.getSwapVerify()) {
            userCardSwapMessage.setDealResult(CommonEnumContainer.DealResult.UNTREATED.getValue())
            ;
        } else {
            userCardSwapMessage.setDealResult(CommonEnumContainer.DealResult.HAS_AGREED_TO.getValue());
        }
        userCardSwapMessageService.insert(new TqUserCardSwapMessage().setEntity(userCardSwapMessage));
        return Result.success(userCardSwapMessage.getDealResult());
    }

    /**
     * 处理请求交换名片的申请
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "处理请求交换名片的申请")
    @PostMapping("deal")
    public Result<Boolean> send(@RequestBody TqDealRequestSwapCardInfo req) {
        CommonEnumContainer.DealResult dealResult = EnumHelperUtil.getByValue(CommonEnumContainer.DealResult.class, req.getDealResult());
        if (dealResult == CommonEnumContainer.DealResult.UNTREATED) {
            throw new CustomerException("处理结果状态不正确", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
        }
        QueryWrapper messageQueryWrapper = WrapperFactory.queryWrapper()
                .select("id,dealResult")
                .eq(TsUserCardSwapMessage.Fields.targetUserId, req.getUserId());
        if (null != req.getId()) {
            messageQueryWrapper.eq(TsUserCardSwapMessage.Fields.id, req.getId());
        }
        if (null != req.getApplicantCardInfoId()) {
            messageQueryWrapper.eq(TsUserCardSwapMessage.Fields.applicantCardInfoId, req.getApplicantCardInfoId());
        }
        if (null != req.getTargetCardInfoId()) {
            messageQueryWrapper.eq(TsUserCardSwapMessage.Fields.targetCardInfoId, req.getTargetCardInfoId());
        }
        TbUserCardSwapMessage message = userCardSwapMessageService.selectOne(messageQueryWrapper);
        Assert.notNull(message, "处理请求不存在！");
        if (!CommonUtil.isEqual(message.getDealResult(), CommonEnumContainer.DealResult.UNTREATED.getValue())) {
            throw new CustomerException("只有未处理的才允许处理", CommonEnumContainer.ResultStatus.THE_DATA_STATE_IS_INCORRECT);
        }
        Assert.notNull(message, "请求不存在");
        message.setDealResult(dealResult.getValue());
        userCardSwapMessageService.updateByPrimary(new TqUserCardSwapMessage().setEntity(message));
        return Result.success(Boolean.TRUE);
    }


}

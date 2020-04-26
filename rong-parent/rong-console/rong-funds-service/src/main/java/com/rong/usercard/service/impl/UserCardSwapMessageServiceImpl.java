package com.rong.usercard.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.CommonUtil;
import com.rong.usercard.mapper.UserCardSwapMessageMapper;
import com.rong.usercard.module.entity.TbUserCardSwapMessage;
import com.rong.usercard.module.request.TqUserCardSwapMessage;
import com.rong.usercard.module.view.TvUserCardSwapMessage;
import com.rong.usercard.service.UserCardSwapMessageService;
import org.springframework.stereotype.Service;

@Service
public class UserCardSwapMessageServiceImpl extends BasicServiceImpl<TbUserCardSwapMessage, TqUserCardSwapMessage, TvUserCardSwapMessage, UserCardSwapMessageMapper> implements UserCardSwapMessageService {

    public static  CommonEnumContainer.UserCardSwapState getSwapState(TbUserCardSwapMessage swapMessage, Long myUserId){
        CommonEnumContainer.UserCardSwapState swapState = CommonEnumContainer.UserCardSwapState.NO_EXCHANGE_REQUEST;
        if(null != swapMessage){
            //自己主动申请的
            boolean force = CommonUtil.isEqual(swapMessage.getApplicantUserId(),myUserId);
            if(CommonUtil.isEqual(CommonEnumContainer.DealResult.UNTREATED.getValue(),swapMessage.getDealResult())){
                swapState = force ? CommonEnumContainer.UserCardSwapState.APPLICATION_FOR_EXCHANGE_HAS_NOT_BEEN_PROCESSED : CommonEnumContainer.UserCardSwapState.THE_APPLICATION_HAS_NOT_BEEN_PROCESSED_BY_YOU;
            }else if(CommonUtil.isEqual(CommonEnumContainer.DealResult.HAS_AGREED_TO.getValue(),swapMessage.getDealResult())){
                swapState = CommonEnumContainer.UserCardSwapState.BOTH_PARTIES_HAVE_AGREED;
            }else if(CommonUtil.isEqual(CommonEnumContainer.DealResult.TARGET_REFUSED_TO.getValue(),swapMessage.getDealResult())){
                swapState = force ? CommonEnumContainer.UserCardSwapState.THE_EXCHANGE_REQUEST_HAS_BEEN_REFUSED : CommonEnumContainer.UserCardSwapState.YOU_VOLUNTARILY_REFUSE_THE_EXCHANGE_REQUEST;
            }
        }
        return swapState;
    }
}
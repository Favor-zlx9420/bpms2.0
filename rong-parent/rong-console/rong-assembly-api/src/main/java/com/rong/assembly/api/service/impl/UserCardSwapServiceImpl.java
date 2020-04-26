package com.rong.assembly.api.service.impl;

import com.rong.assembly.api.service.UserCardSwapService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.mapper.UserCardSwapMessageMapper;
import com.rong.usercard.module.entity.TbUserCardSwapMessage;
import com.rong.usercard.module.query.TsUserCardSwapMessage;
import com.rong.usercard.service.impl.UserCardSwapMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCardSwapServiceImpl implements UserCardSwapService {
    @Autowired
    private UserCardSwapMessageMapper userCardSwapMessageMapper;
    @Override
    public CommonEnumContainer.UserCardSwapState getBothSidesSwapState(Long myUserId, Long theUserId) {
        TbUserCardSwapMessage swapMessage =
                userCardSwapMessageMapper.selectOne(
                        WrapperFactory.queryWrapper()
                                .or(x->x
                                        .and(y->y
                                                .eq(TsUserCardSwapMessage.Fields.applicantUserId,myUserId)//自己申请
                                                .eq(TsUserCardSwapMessage.Fields.targetUserId,theUserId)//对方用户id
                                        )
                                        .and(y->y
                                                .eq(TsUserCardSwapMessage.Fields.applicantUserId,theUserId)//对方申请
                                                .eq(TsUserCardSwapMessage.Fields.targetUserId,myUserId)//自己的id
                                        )
                                )
                )
                ;
        return UserCardSwapMessageServiceImpl.getSwapState(swapMessage,myUserId);
    }
}

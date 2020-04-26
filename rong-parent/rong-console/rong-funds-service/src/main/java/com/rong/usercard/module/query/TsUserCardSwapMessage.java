package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardSwapMessage;
import lombok.Data;

@Data
public class TsUserCardSwapMessage extends QueryInfo<TbUserCardSwapMessage> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        applicantUserId,
        targetUserId,
        applicantCardInfoId,
        targetCardInfoId,
        dealResult;
    }
}
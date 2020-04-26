package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemCreditHistory;
import lombok.Data;

@Data
public class TsMemCreditHistory extends QueryInfo<TbMemCreditHistory> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        memberId,
        recordType,
        creditValue,
        balance,
        memo,
        relationLinks,
        admUserId,
        admUserName;
    }
}
package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemCreditfileCertifyLog;
import lombok.Data;

@Data
public class TsMemCreditfileCertifyLog extends QueryInfo<TbMemCreditfileCertifyLog> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        memberId,
        creditfileState,
        creditfileType,
        relationLinks,
        description,
        admUserId,
        admUserName,
        memo,
        ip;
    }
}
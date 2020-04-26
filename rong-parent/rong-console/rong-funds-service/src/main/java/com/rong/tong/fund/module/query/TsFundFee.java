package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundFee;
import lombok.Data;

@Data
public class TsFundFee extends QueryInfo<TbFundFee> {

    public enum Fields {
        id,
        securityId,
        publishDate,
        isExe,
        beginDate,
        endDate,
        chargeType,
        chargeMode,
        chargeExchCd,
        chargeP,
        clientType,
        minCharRate,
        maxCharRate,
        chargeUnit,
        chargeDesc,
        charConDesc,
        charCon1,
        charConNo1,
        charConUnit1,
        charStart1,
        charEnd1,
        isCharStart1,
        isCharEnd1,
        charCon2,
        charConNo2,
        charConUnit2,
        charStart2,
        charEnd2,
        isCharStart2,
        isCharEnd2,
        notes,
        updateTime,
        tmstamp;
    }
}
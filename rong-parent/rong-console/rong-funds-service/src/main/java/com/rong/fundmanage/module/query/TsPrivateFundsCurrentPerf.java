package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentPerf;
import lombok.Data;

@Data
public class TsPrivateFundsCurrentPerf extends QueryInfo<TbPrivateFundsCurrentPerf> {

    public enum Fields {
        id,
        securityId,
        window,
        returnOfLatestYear,
        endDate,
        returnOfEstablish,
        returnOfLatestMonth,
        returnOfThisYear
    }
}
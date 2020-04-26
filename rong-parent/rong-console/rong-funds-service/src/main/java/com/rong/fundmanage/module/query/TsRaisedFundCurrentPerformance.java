package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentPerformance;
import lombok.Data;

@Data
public class TsRaisedFundCurrentPerformance extends QueryInfo<TbRaisedFundCurrentPerformance> {

    public enum Fields {
        id,
        createDate,
        securityId,
        endDate,
        returnRate1w,
        returnRate1y,
        stdev,
        sharpeRatio,
        personId,
        partyId;
    }
}
package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundPerformance;
import lombok.Data;

@Data
public class TsFundPerformance extends QueryInfo<TbFundPerformance> {

    public enum Fields {
        id,
        securityId,
        endDate,
        returnRate1w,
        returnRate1y,
        averageReturnRate,
        stdev,
        sharpeRatio,
        updateTime,
        tmstamp;
    }
}
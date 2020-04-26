package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundMperf;
import lombok.Data;

@Data
public class TsFundMperf extends QueryInfo<TbFundMperf> {

    public enum Fields {
        id,
        securityId,
        endDate,
        window,
        avgDailyProfit,
        varDailyProfit,
        avgWeeklyYield,
        varWeeklyYield,
        updateTime,
        tmstamp;
    }
}
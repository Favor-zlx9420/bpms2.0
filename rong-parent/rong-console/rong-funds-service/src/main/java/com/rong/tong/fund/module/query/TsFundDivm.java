package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundDivm;
import lombok.Data;

@Data
public class TsFundDivm extends QueryInfo<TbFundDivm> {

    public enum Fields {
        id,
        securityId,
        publishDate,
        endDate,
        currencyCd,
        dailyProfit,
        weeklyYield,
        updateTime,
        nav,
        tmstamp;
    }
}
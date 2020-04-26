package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundAdjNav;
import lombok.Data;

@Data
public class TsFundAdjNav extends QueryInfo<TbFundAdjNav> {

    public enum Fields {
        id,
        securityId,
        endDate,
        adjNav,
        adjFactor,
        returnRate,
        updateTime,
        tmstamp;
    }
}
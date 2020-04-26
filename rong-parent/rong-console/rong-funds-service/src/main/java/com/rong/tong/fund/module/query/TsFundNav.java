package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundNav;
import lombok.Data;

@Data
public class TsFundNav extends QueryInfo<TbFundNav> {

    public enum Fields {
        id,
        securityId,
        publishDate,
        endDate,
        currencyCd,
        nav,
        accumNav,
        updateTime,
        tmstamp;
    }
}
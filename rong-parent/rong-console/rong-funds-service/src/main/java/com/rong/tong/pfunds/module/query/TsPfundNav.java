package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundNav;
import lombok.Data;

@Data
public class TsPfundNav extends QueryInfo<TbPfundNav> {

    public enum Fields {
        id,
        securityId,
        publishDate,
        endDate,
        nav,
        accumNav,
        updateTime,
        tmstamp,
        adjNav,
        navUnit,
        returnRate,
        adjReturnRate;
    }
}
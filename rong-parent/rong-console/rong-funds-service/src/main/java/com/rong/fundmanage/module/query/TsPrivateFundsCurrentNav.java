package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import lombok.Data;

@Data
public class TsPrivateFundsCurrentNav extends QueryInfo<TbPrivateFundsCurrentNav> {

    public enum Fields {
        id,
        securityId,
        nav,
        accumNav,
        endDate,
        adjNav,
        returnRate
    }
}
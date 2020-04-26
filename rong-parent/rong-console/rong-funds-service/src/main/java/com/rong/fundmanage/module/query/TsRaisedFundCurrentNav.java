package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNav;
import lombok.Data;

@Data
public class TsRaisedFundCurrentNav extends QueryInfo<TbRaisedFundCurrentNav> {

    public enum Fields {
        id,
        createDate,
        securityId,
        endDate,
        nav,
        accumNav,
        personId,
        partyId,
        adjNav,
        returnRate
    }
}
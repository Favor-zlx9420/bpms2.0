package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundManagerNew;
import lombok.Data;

@Data
public class TsFundManagerNew extends QueryInfo<TbFundManagerNew> {

    public enum Fields {
        id,
        personId,
        fundId,
        securityId,
        publishDate,
        position,
        isIncumbent,
        accessionDate,
        dimissionDate,
        partyId,
        updateTime,
        tmstamp;
    }
}
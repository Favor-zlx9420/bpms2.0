package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundInstRep;
import lombok.Data;

@Data
public class TsPfundInstRep extends QueryInfo<TbPfundInstRep> {

    public enum Fields {
        id,
        partyId,
        endDate,
        securityId,
        numAll,
        numDura,
        updateTime,
        tmstamp;
    }
}
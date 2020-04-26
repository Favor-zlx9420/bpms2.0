package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundManagerRep;
import lombok.Data;

@Data
public class TsPfundManagerRep extends QueryInfo<TbPfundManagerRep> {

    public enum Fields {
        id,
        personId,
        endDate,
        securityId,
        numAll,
        numDura,
        updateTime,
        tmstamp;
    }
}
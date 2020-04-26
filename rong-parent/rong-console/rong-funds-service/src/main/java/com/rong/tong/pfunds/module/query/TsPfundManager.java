package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
import lombok.Data;

@Data
public class TsPfundManager extends QueryInfo<TbPfundManager> {

    public enum Fields {
        id,
        personId,
        securityId,
        publishDate,
        position,
        isIncumbent,
        accessionDate,
        dimissionDate,
        updateTime,
        tmstamp,
        partyId;
    }
}
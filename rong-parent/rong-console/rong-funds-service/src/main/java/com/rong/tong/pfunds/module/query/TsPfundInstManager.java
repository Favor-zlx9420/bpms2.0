package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundInstManager;
import lombok.Data;

@Data
public class TsPfundInstManager extends QueryInfo<TbPfundInstManager> {

    public enum Fields {
        id,
        partyId,
        personId,
        name,
        position,
        beginDate,
        endDate,
        updateTime,
        tmstamp,
        background,
        isMana,
        backgroundDesc;
    }
}
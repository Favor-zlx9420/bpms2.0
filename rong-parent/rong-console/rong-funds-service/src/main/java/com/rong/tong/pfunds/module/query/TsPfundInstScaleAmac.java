package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundInstScaleAmac;
import lombok.Data;

@Data
public class TsPfundInstScaleAmac extends QueryInfo<TbPfundInstScaleAmac> {

    public enum Fields {
        id,
        partyId,
        partyFullName,
        mainFundType,
        scale,
        instId,
        num,
        isNew,
        fetchDate,
        updateTime,
        tmstamp;
    }
}
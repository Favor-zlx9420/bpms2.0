package com.rong.roadshow.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.roadshow.module.entity.TbRoadShowHotOrg;
import lombok.Data;

@Data
public class TsRoadShowHotOrg extends QueryInfo<TbRoadShowHotOrg> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        sort,
        state,
        partyId;
    }
}
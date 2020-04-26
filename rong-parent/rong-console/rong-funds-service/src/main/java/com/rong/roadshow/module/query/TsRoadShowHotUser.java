package com.rong.roadshow.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.roadshow.module.entity.TbRoadShowHotUser;
import lombok.Data;

@Data
public class TsRoadShowHotUser extends QueryInfo<TbRoadShowHotUser> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        sort,
        state,
        presenter,
        userId,
        description;
    }
}
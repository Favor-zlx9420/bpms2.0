package com.rong.roadshow.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.roadshow.module.entity.TbUserRoadShowFav;
import lombok.Data;

@Data
public class TsUserRoadShowFav extends QueryInfo<TbUserRoadShowFav> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        roadShowId;
    }
}
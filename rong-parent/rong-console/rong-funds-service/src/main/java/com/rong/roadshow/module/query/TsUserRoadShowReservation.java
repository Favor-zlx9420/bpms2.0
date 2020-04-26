package com.rong.roadshow.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.roadshow.module.entity.TbUserRoadShowView;
import lombok.Data;

@Data
public class TsUserRoadShowReservation extends QueryInfo<TbUserRoadShowView> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        roadShowId;
    }
}
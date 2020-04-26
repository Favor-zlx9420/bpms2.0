package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbUserReservation;
import lombok.Data;

@Data
public class TsUserReservation extends QueryInfo<TbUserReservation> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        name,
        phone,
        dealStatus,
        targetId,
        type,
        reservationUserId,
        dualUserId
    }
}
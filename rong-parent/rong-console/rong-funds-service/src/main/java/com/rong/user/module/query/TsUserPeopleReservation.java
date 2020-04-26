package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserPeopleReservation;
import lombok.Data;

@Data
public class TsUserPeopleReservation extends QueryInfo<TbUserPeopleReservation> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        userId,
        reservationUserId;
    }
}
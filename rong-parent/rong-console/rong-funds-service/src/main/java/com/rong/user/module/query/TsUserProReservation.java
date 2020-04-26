package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserProReservation;
import lombok.Data;

@Data
public class TsUserProReservation extends QueryInfo<TbUserProReservation> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        userId,
        securityId,
        type;
    }
}
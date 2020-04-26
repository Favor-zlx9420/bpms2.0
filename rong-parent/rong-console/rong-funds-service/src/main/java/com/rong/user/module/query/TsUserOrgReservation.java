package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserOrgReservation;
import lombok.Data;

@Data
public class TsUserOrgReservation extends QueryInfo<TbUserOrgReservation> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        userId,
        partyId;
    }
}
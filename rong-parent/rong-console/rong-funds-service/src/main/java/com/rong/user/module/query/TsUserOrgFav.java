package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserOrgFav;
import lombok.Data;

@Data
public class TsUserOrgFav extends QueryInfo<TbUserOrgFav> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        partyId;
    }
}
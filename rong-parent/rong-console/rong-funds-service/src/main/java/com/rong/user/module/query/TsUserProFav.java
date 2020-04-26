package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserProFav;
import lombok.Data;

@Data
public class TsUserProFav extends QueryInfo<TbUserProFav> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        securityId,
        type;
    }
}
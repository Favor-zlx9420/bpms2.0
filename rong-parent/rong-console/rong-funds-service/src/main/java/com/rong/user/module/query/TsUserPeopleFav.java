package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserPeopleFav;
import lombok.Data;

@Data
public class TsUserPeopleFav extends QueryInfo<TbUserPeopleFav> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        favUserId,
        personId
    }
}
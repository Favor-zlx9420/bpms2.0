package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardLike;
import lombok.Data;

@Data
public class TsUserCardLike extends QueryInfo<TbUserCardLike> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        likorUserId,
        cardInfoId;
    }
}
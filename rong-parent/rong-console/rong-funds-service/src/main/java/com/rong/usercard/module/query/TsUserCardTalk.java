package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardTalk;
import lombok.Data;

@Data
public class TsUserCardTalk extends QueryInfo<TbUserCardTalk> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        content;
    }
}
package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardModule;
import lombok.Data;

@Data
public class TsUserCardModule extends QueryInfo<TbUserCardModule> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        sort,
        userId,
        type;
    }
}
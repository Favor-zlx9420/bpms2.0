package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardShareTitle;
import lombok.Data;

@Data
public class TsUserCardShareTitle extends QueryInfo<TbUserCardShareTitle> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        state,
        title;
    }
}
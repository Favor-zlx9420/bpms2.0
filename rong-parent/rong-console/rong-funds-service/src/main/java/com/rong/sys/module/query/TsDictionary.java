package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbDictionary;
import lombok.Data;

@Data
public class TsDictionary extends QueryInfo<TbDictionary> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        type,
        key,
        value,
        description,
        valueType;
    }
}
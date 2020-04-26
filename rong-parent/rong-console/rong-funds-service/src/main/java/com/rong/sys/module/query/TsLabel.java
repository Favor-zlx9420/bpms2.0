package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbLabel;
import lombok.Data;

@Data
public class TsLabel extends QueryInfo<TbLabel> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        sort,
        state,
        name,
        description,
        type;
    }
}
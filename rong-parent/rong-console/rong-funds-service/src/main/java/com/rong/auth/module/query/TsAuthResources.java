package com.rong.auth.module.query;

import com.rong.auth.module.entity.TbAuthResources;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAuthResources extends QueryInfo<TbAuthResources> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        name,
        urlPattern,
        type;
    }
}
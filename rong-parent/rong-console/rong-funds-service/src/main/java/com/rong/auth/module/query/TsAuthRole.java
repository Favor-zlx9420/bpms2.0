package com.rong.auth.module.query;

import com.rong.auth.module.entity.TbAuthRole;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAuthRole extends QueryInfo<TbAuthRole> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        name,
        description,
        symbol;
    }
}
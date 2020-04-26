package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbOrgInfo;
import lombok.Data;

@Data
public class TsOrgInfo extends QueryInfo<TbOrgInfo> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        name,
        shortName,
        servicePhone,
        area,
        netUrl,
        type;
    }
}
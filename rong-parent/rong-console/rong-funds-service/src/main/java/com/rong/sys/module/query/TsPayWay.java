package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbPayWay;
import lombok.Data;

@Data
public class TsPayWay extends QueryInfo<TbPayWay> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        name,
        description;
    }
}
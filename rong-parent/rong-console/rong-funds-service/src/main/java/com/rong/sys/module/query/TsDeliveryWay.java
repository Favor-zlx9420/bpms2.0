package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbDeliveryWay;
import lombok.Data;

@Data
public class TsDeliveryWay extends QueryInfo<TbDeliveryWay> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        name,
        price,
        description;
    }
}
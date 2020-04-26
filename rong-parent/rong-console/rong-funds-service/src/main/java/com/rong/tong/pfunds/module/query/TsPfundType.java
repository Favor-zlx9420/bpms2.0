package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundType;
import lombok.Data;

@Data
public class TsPfundType extends QueryInfo<TbPfundType> {

    public enum Fields {
        id,
        securityId,
        codeTypeId,
        valueNumCd,
        updateTime,
        tmstamp;
    }
}
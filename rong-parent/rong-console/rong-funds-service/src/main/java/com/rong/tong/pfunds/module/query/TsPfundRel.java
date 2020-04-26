package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundRel;
import lombok.Data;

@Data
public class TsPfundRel extends QueryInfo<TbPfundRel> {

    public enum Fields {
        id,
        securityId,
        relSecurityId,
        level,
        type,
        updateTime,
        tmstamp;
    }
}
package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundManaYear;
import lombok.Data;

@Data
public class TsPfundManaYear extends QueryInfo<TbPfundManaYear> {

    public enum Fields {
        id,
        personId,
        year,
        updateTime,
        tmstamp;
    }
}
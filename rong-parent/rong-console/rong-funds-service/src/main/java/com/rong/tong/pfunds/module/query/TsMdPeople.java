package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import lombok.Data;

@Data
public class TsMdPeople extends QueryInfo<TbMdPeople> {

    public enum Fields {
        personId,
        name,
        gender,
        birthday,
        updateTime,
        tmstamp,
        education,
        backgroundDesc;
    }
}
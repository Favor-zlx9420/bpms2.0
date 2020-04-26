package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundResume;
import lombok.Data;

@Data
public class TsPfundResume extends QueryInfo<TbPfundResume> {

    public enum Fields {
        id,
        personId,
        name,
        acceYear,
        acceMonth,
        dimiYear,
        dimiMonth,
        partyId,
        partyFullName,
        position,
        updateTime,
        tmstamp,
        isIncumbent;
    }
}
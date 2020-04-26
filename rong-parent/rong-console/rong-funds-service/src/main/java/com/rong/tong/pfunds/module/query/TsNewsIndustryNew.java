package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbNewsIndustryNew;
import lombok.Data;

@Data
public class TsNewsIndustryNew extends QueryInfo<TbNewsIndustryNew> {

    public enum Fields {
        id,
        newsId,
        industryName1st,
        industryName2nd,
        insertTime,
        updateTime,
        newsGenre,
        tmstamp;
    }
}
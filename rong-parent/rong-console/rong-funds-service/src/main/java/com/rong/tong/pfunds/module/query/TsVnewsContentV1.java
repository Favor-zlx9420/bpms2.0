package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbVnewsContentV1;
import lombok.Data;

@Data
public class TsVnewsContentV1 extends QueryInfo<TbVnewsContentV1> {

    public enum Fields {
        newsId,
        newsOriginSource,
        newsAuthor,
        newsUrl,
        newsTitle,
        newsPublishSite,
        newsPublishTime,
        effectiveTime,
        updateTime,
        tmstamp;
    }
}
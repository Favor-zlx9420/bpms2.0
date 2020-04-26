package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbBanner;
import lombok.Data;

@Data
public class TsBanner extends QueryInfo<TbBanner> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        pageType,
        title,
        description,
        picUrl,
        link,
        createBy;
    }
}
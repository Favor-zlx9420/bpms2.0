package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbAdvertise;
import lombok.Data;

@Data
public class TsAdvertise extends QueryInfo<TbAdvertise> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        type,
        fileUrl,
        imgSamUrl,
        title,
        content,
        link;
    }
}
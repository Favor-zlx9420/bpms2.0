package com.rong.roadshow.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.roadshow.module.entity.TbRoadShowCategory;
import lombok.Data;

@Data
public class TsRoadShowCategory extends QueryInfo<TbRoadShowCategory> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        parentId,
        name,
        keyword,
        description,
        labelIds,
        createBy;
    }
}
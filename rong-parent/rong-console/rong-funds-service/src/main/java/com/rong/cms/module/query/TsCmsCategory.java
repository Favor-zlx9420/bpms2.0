package com.rong.cms.module.query;

import com.rong.cms.module.entity.TbCmsCategory;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsCmsCategory extends QueryInfo<TbCmsCategory> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        typeId,
        parentId,
        name,
        keyword,
        description;
    }
}
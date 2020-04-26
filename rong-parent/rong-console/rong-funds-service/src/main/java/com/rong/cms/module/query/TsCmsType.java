package com.rong.cms.module.query;

import com.rong.cms.module.entity.TbCmsType;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsCmsType extends QueryInfo<TbCmsType> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        name,
        description;
    }
}
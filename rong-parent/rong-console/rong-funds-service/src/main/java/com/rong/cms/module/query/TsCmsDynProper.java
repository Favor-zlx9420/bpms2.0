package com.rong.cms.module.query;

import com.rong.cms.module.entity.TbCmsDynProper;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsCmsDynProper extends QueryInfo<TbCmsDynProper> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        sort,
        typeId,
        htmlType,
        name,
        value;
    }
}
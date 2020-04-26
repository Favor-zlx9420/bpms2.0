package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbRegion;
import lombok.Data;

@Data
public class TsRegion extends QueryInfo<TbRegion> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        code,
        name,
        parentId,
        level,
        pinyin,
        shortPinyin;
    }
}
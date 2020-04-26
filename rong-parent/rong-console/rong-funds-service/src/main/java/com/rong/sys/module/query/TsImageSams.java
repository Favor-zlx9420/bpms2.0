package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbImageSams;
import lombok.Data;

@Data
public class TsImageSams extends QueryInfo<TbImageSams> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        labelIds,
        lmtSrc,
        cntSrc,
        bigSrc,
        name,
        type,
        description;
    }
}
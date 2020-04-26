package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbAvailableBank;
import lombok.Data;

@Data
public class TsAvailableBank extends QueryInfo<TbAvailableBank> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        type,
        name,
        shortName,
        code,
        memo,
        platform,
        picUrl;
    }
}
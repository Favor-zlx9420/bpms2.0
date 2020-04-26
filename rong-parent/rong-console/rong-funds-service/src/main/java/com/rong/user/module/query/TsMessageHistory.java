package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbMessageHistory;
import lombok.Data;

@Data
public class TsMessageHistory extends QueryInfo<TbMessageHistory> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        codeType,
        contentType,
        title,
        content,
        relationUuid,
        target,
        local,
        ip;
    }
}
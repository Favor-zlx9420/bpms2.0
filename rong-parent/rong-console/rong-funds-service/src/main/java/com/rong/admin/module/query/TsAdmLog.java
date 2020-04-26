package com.rong.admin.module.query;

import com.rong.admin.module.entity.TbAdmLog;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAdmLog extends QueryInfo<TbAdmLog> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        columnId,
        time,
        admUserName,
        admUserId,
        operaDesc,
        uri,
        returnValue,
        otherArgs,
        ip,
        arguments;
    }
}
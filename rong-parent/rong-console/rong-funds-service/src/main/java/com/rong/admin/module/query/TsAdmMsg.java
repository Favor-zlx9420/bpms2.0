package com.rong.admin.module.query;

import com.rong.admin.module.entity.TbAdmMsg;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAdmMsg extends QueryInfo<TbAdmMsg> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        type,
        toAdmUserId,
        fromAdmUserId,
        fromMemberId,
        title,
        content,
        labelIds;
    }
}
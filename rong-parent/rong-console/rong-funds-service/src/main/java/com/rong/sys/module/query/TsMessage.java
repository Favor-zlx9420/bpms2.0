package com.rong.sys.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.sys.module.entity.TbMessage;
import lombok.Data;

@Data
public class TsMessage extends QueryInfo<TbMessage> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        memberId,
        adminUserId,
        title,
        content,
        viewState,
        type,
        parentId,
        contactor,
        phone,
        email,
        post,
        company,
        province,
        city,
        address,
        ip,
        reply;
    }
}
package com.rong.admin.module.query;

import com.rong.admin.module.entity.TbAdmMemorandum;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAdmMemorandum extends QueryInfo<TbAdmMemorandum> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        admUserId,
        title,
        content,
        remindTime,
        labelIds;
    }
}
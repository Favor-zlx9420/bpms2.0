package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbUserComment;
import lombok.Data;

@Data
public class TsUserComment extends QueryInfo<TbUserComment> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        commentUserId,
        visible,
        content,
        auditUserId,
        commentUserName,
        auditUserName,
        targetId,
        type,
        auditResult
    }
}
package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserCommentReply;
import lombok.Data;

@Data
public class TsUserCommentReply extends QueryInfo<TbUserCommentReply> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        visible,
        replyUserId,
        content,
        auditUserId,
        replyUserName,
        auditUserName,
        commentId,
        auditResult;
    }
}
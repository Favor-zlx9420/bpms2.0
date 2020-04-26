package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserFeedBack;
import lombok.Data;

@Data
public class TsUserFeedBack extends QueryInfo<TbUserFeedBack> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        submitUserId,
        visible,
        title,
        content,
        link,
        replyUserId,
        replyContent,
        result;
    }
}
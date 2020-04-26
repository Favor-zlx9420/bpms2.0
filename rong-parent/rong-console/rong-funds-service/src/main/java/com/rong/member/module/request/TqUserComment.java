package com.rong.member.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.member.module.entity.TbUserComment;
import lombok.Data;

@Data
public class TqUserComment extends BaseRequest<TbUserComment, TqUserComment> {
    private String rejectReason;
}
package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUserCommentReply extends TqPageListBase {
    @ApiModelProperty(value = "评论id",required = true)
    @RequireValidator
    private Long commentId;
}

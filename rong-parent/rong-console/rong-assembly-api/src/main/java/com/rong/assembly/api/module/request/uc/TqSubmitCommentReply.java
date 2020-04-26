package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqSubmitCommentReply extends TqUserAuthBase {
    @ApiModelProperty(value = "评论id",required = true)
    @RequireValidator
    private Long commentId;
    @ApiModelProperty(value = "回复内容",required = true)
    @RequireValidator
    private String content;
}

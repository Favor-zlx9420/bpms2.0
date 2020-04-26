package com.rong.user.module.view;

import com.rong.user.module.entity.TbUserCommentReply;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvUserCommentReply extends TbUserCommentReply {
    @ApiModelProperty("回复用户姓名")
    private String realName;
    @ApiModelProperty("回复用户头像地址")
    private String headPortrait;
    private String targetInfo;
    private String commentContent;
    private Integer commentType;
}
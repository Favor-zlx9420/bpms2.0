package com.rong.member.module.view;

import com.rong.member.module.entity.TbUserComment;
import com.rong.user.module.view.TvUserCommentReply;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TvUserComment extends TbUserComment {
    @ApiModelProperty("评论用户真实姓名")
    private String realName;
    @ApiModelProperty("评论用户头像地址")
    private String headPortrait;
    @ApiModelProperty("回复列表")
    private List<TvUserCommentReply> replies;
    private String targetInfo;
}
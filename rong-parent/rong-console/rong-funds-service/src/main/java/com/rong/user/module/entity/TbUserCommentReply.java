package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户评论回复
 * @author      : lether
 * @createDate  : 2020-02-20
 */
@Table("`tb_user_comment_reply`")
@Data()
@Accessors(chain = true)
public class TbUserCommentReply extends BaseEntity<TbUserCommentReply> {
    /**
     * 是否可见
     */
    @Column("`visible`")
    @ApiModelProperty("是否可见")
    private Boolean visible;

    /**
     * 回复用户id
     */
    @Column("`reply_user_id`")
    @ApiModelProperty("回复用户id")
    private Long replyUserId;

    /**
     * 回复内容
     */
    @Column("`content`")
    @ApiModelProperty("回复内容")
    private String content;

    /**
     * 审核用户id
     */
    @Column("`audit_user_id`")
    @ApiModelProperty("审核用户id")
    private Long auditUserId;

    /**
     * 回复用户名称
     */
    @Column("`reply_user_name`")
    @ApiModelProperty("回复用户名称")
    private String replyUserName;

    /**
     * 审核用户名称
     */
    @Column("`audit_user_name`")
    @ApiModelProperty("审核用户名称")
    private String auditUserName;

    /**
     * 评论内容id
     */
    @Column("`comment_id`")
    @ApiModelProperty("评论内容id")
    private Long commentId;

    /**
     * 审核结果（0：未审核，1：审核通过，-1：审核未通过）
     */
    @Column("`audit_result`")
    @ApiModelProperty("审核结果（0：未审核，1：审核通过，-1：审核未通过）")
    private Integer auditResult;
}
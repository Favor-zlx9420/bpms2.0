package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户意见反馈
 * @author      : lether
 * @createDate  : 2020-02-21
 */
@Table("`tb_user_feedback`")
@Data()
@Accessors(chain = true)
public class TbUserFeedBack extends BaseEntity<TbUserFeedBack> {
    /**
     * 反馈用户id
     */
    @Column("`submit_user_id`")
    @ApiModelProperty("反馈用户id")
    private Long submitUserId;

    /**
     * 是否可见
     */
    @Column("`visible`")
    @ApiModelProperty("是否可见")
    private Boolean visible;

    /**
     * 反馈标题
     */
    @Column("`title`")
    @ApiModelProperty("反馈标题")
    private String title;

    /**
     * 反馈内容
     */
    @Column("`content`")
    @ApiModelProperty("反馈内容")
    private String content;

    /**
     * 相关连接
     */
    @Column("`link`")
    @ApiModelProperty("相关连接")
    private String link;

    /**
     * 回复用户id
     */
    @Column("`reply_user_id`")
    @ApiModelProperty("回复用户id")
    private Long replyUserId;

    /**
     * 回复内容
     */
    @Column("`reply_content`")
    @ApiModelProperty("回复内容")
    private String replyContent;

    /**
     * 处理结果（0：未处理，1：已处理）
     */
    @Column("`result`")
    @ApiModelProperty("处理结果（0：未处理，1：已处理）")
    private Integer result;
}
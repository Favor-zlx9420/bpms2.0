package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户评论
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_user_comment`")
@Data()
@Accessors(chain = true)
public class TbUserComment extends BaseEntity<TbUserComment> {
    /**
     * 评论用户id
     */
    @Column("`comment_user_id`")
    @ApiModelProperty("评论用户id")
    private Long commentUserId;

    /**
     * 是否可见
     */
    @Column("`visible`")
    private Boolean visible;

    /**
     * 评论内容
     */
    @Column("`content`")
    @ApiModelProperty("评论内容")
    private String content;

    /**
     * 审核用户id
     */
    @Column("`audit_user_id`")
    private Long auditUserId;

    /**
     * 评论用户名称
     */
    @Column("`comment_user_name`")
    @ApiModelProperty("评论用户名称")
    private String commentUserName;

    /**
     * 审核用户名称
     */
    @Column("`audit_user_name`")
    @ApiModelProperty("审核用户名称")
    private String auditUserName;

    /**
     * 评论对象id
     */
    @Column("`target_id`")
    @ApiModelProperty(value = "评论类型（评论对象id,机构为partyId，产品为securityId，基金经理为personId）",required = true)
    private Long targetId;

    /**
     * 评论类型（0：机构；1：基金经理；2：产品）
     */
    @Column("`type`")
    private Integer type;
    /**
     * 审核结果（0：未审核，1：审核通过，-1：审核未通过）
     */
    @Column("`audit_result`")
    @ApiModelProperty("审核结果（0：未审核，1：审核通过，-1：审核未通过）")
    private Integer auditResult;

}
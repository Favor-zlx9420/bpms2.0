package com.rong.store.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 直营店用户服务记录
 * @author      : lether
 * @createDate  : 2020-02-14
 */
@Table("`tb_direct_store_service_history`")
@Data()
@Accessors(chain = true)
public class TbDirectStoreServiceHistory extends BaseEntity<TbDirectStoreServiceHistory> {
    /**
     * 直营店客服/基金经理/机构代理用户id
     */
    @Column("`customer_user_id`")
    @ApiModelProperty("直营店客服/基金经理/机构代理用户id")
    private Long customerUserId;

    /**
     * 被服务用户id
     */
    @Column("`investor_user_id`")
    @ApiModelProperty("被服务用户id")
    private Long investorUserId;

    /**
     * 服务者所属机构
     */
    @Column("`party_id`")
    @ApiModelProperty("服务者所属机构")
    private Long partyId;

    /**
     * 服务内容
     */
    @Column("`content`")
    @ApiModelProperty("服务内容")
    private String content;

    /**
     * 管理者审核结果（0：未审核，1：属实，-1：非实）
     */
    @Column("`audit_result`")
    @ApiModelProperty("管理者审核结果（0：未审核，1：属实，-1：非实）")
    private Integer auditResult;

    /**
     * 评分（0-5分）
     */
    @Column("`score`")
    @ApiModelProperty("评分（0-5分）")
    private Integer score;

    /**
     * 客户评论
     */
    @Column("`comment`")
    @ApiModelProperty("客户评论")
    private String comment;

    /**
     * 服务内容关联url
     */
    @Column("`link_url`")
    @ApiModelProperty("服务内容关联url")
    private String linkUrl;

    /**
     * 服务内容图片截图url
     */
    @Column("`pic_url`")
    @ApiModelProperty("服务内容图片截图url")
    private String picUrl;

    /**
     *
     */
    @Column("`type`")
    @ApiModelProperty("0:产品，1机构")
    private Integer type;
}
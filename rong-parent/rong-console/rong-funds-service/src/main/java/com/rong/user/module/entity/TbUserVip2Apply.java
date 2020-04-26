package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户申请蓝vip
 * @author      : Administrator
 * @createDate  : 2020-03-03
 */
@Table("`tb_user_vip2_apply`")
@Data()
@Accessors(chain = true)
public class TbUserVip2Apply extends BaseEntity<TbUserVip2Apply> {
    /**
     * 审核状态（0：待审核，1：审核通过，2：再次提交，3未通过审核）
     */
    @Column("`audit_result`")
    @ApiModelProperty("审核状态（0：待审核，1：审核通过，2：再次提交，3未通过审核）")
    private Integer auditResult;

    /**
     * 申请用户id
     */
    @Column("`app_user_id`")
    @ApiModelProperty("申请用户id")
    private Long appUserId;

    /**
     * 审核用户id
     */
    @Column("`audit_user_id`")
    @ApiModelProperty("审核用户id")
    private Long auditUserId;

    /**
     * 机构全称
     */
    @Column("`party_full_name`")
    @ApiModelProperty("机构全称")
    private String partyFullName;

    /**
     * 部门名称
     */
    @Column("`depart_name`")
    @ApiModelProperty("部门名称")
    private String departName;

    /**
     * 职位
     */
    @Column("`position`")
    @ApiModelProperty("职位")
    private String position;

    /**
     * 备注
     */
    @Column("`remark`")
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 名片图片1
     */
    @Column("`biz_card_url1`")
    @ApiModelProperty("名片图片1")
    private String bizCardUrl1;

    /**
     * 名片图片2
     */
    @Column("`biz_card_url2`")
    @ApiModelProperty("名片图片2")
    private String bizCardUrl2;

    /**
     * 手持证件照1
     */
    @Column("`holding_photo_url1`")
    @ApiModelProperty("手持证件照1")
    private String holdingPhotoUrl1;

    /**
     * 手持证件照2
     */
    @Column("`holding_photo_url2`")
    @ApiModelProperty("手持证件照2")
    private String holdingPhotoUrl2;

    /**
     * 转账截图1
     */
    @Column("`transfer_info_url1`")
    @ApiModelProperty("转账截图1")
    private String transferInfoUrl1;

    /**
     * 转账截图2
     */
    @Column("`transfer_info_url2`")
    @ApiModelProperty("转账截图2")
    private String transferInfoUrl2;

    /**
     * 转账截图3
     */
    @Column("`transfer_info_url3`")
    @ApiModelProperty("转账截图3")
    private String transferInfoUrl3;

    /**
     * 转账截图4
     */
    @Column("`transfer_info_url4`")
    @ApiModelProperty("转账截图4")
    private String transferInfoUrl4;
}
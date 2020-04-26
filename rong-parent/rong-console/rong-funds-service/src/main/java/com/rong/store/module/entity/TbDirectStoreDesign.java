package com.rong.store.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 直营店装潢
 * @author      : Administrator
 * @createDate  : 2020-02-27
 */
@Table("`tb_direct_store_design`")
@Data()
@Accessors(chain = true)
public class TbDirectStoreDesign extends BaseEntity<TbDirectStoreDesign> {
    /**
     * 是否可见
     */
    @Column("`visible`")
    @ApiModelProperty("是否可见")
    private Boolean visible;

    /**
     * 机构id
     */
    @Column("`party_id`")
    @ApiModelProperty("机构id")
    private Long partyId;

    /**
     * 标题
     */
    @Column("`title`")
    @ApiModelProperty("标题")
    private String title;

    /**
     * 副标题
     */
    @Column("`sub_title`")
    @ApiModelProperty("副标题")
    private String subTitle;

    /**
     * 内容
     */
    @Column("`content`")
    @ApiModelProperty("内容")
    private String content;

    /**
     * 排序，越小在越前面
     */
    @Column("`sort`")
    @ApiModelProperty("排序，越小在越前面")
    private BigDecimal sort;

    /**
     * 审核用户id
     */
    @Column("`audit_user_id`")
    @ApiModelProperty("审核用户id")
    private Long auditUserId;

    /**
     * 申请用户id
     */
    @Column("`app_user_id`")
    @ApiModelProperty("申请用户id")
    private Long appUserId;

    /**
     * 0:未审核，1：通过审核，2再次提交，3：未通过审核
     */
    @Column("`audit_state`")
    @ApiModelProperty("0:未审核，1：通过审核，2再次提交，3：未通过审核")
    private Integer auditState;
}
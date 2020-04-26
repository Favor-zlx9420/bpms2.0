package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户申请金vip
 * @author      : Administrator
 * @createDate  : 2020-03-03
 */
@Table("`tb_user_vip1_apply`")
@Data()
@Accessors(chain = true)
public class TbUserVip1Apply extends BaseEntity<TbUserVip1Apply> {
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
     * 备注
     */
    @Column("`remark`")
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 资料认证1
     */
    @Column("`certificate1_url`")
    @ApiModelProperty("资料认证1")
    private String certificate1Url;

    /**
     * 资料认证2
     */
    @Column("`certificate2_url`")
    @ApiModelProperty("资料认证2")
    private String certificate2Url;

    /**
     * 资料认证3
     */
    @Column("`certificate3_url`")
    @ApiModelProperty("资料认证3")
    private String certificate3Url;

    /**
     * 资料认证4
     */
    @Column("`certificate4_url`")
    @ApiModelProperty("资料认证4")
    private String certificate4Url;

    /**
     * 资料认证5
     */
    @Column("`certificate5_url`")
    @ApiModelProperty("资料认证5")
    private String certificate5Url;

    /**
     * 资料认证6
     */
    @Column("`certificate6_url`")
    @ApiModelProperty("资料认证6")
    private String certificate6Url;

    /**
     * 资料认证7
     */
    @Column("`certificate7_url`")
    @ApiModelProperty("资料认证7")
    private String certificate7Url;

    /**
     * 资料认证8
     */
    @Column("`certificate8_url`")
    @ApiModelProperty("资料认证8")
    private String certificate8Url;

    /**
     * 资料认证9
     */
    @Column("`certificate9_url`")
    @ApiModelProperty("资料认证9")
    private String certificate9Url;

    /**
     * 资料认证10
     */
    @Column("`certificate10_url`")
    @ApiModelProperty("资料认证10")
    private String certificate10Url;
}
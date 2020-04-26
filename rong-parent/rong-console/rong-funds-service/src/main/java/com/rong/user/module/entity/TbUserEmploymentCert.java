package com.rong.user.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.PrimaryKey;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description : 用户金融从业认证信息表
 * @author      : admin
 * @createDate  : 2020-04-23
 */
@Table("`tb_user_employment_cert`")
@Data()
@Accessors(chain = true)
public class TbUserEmploymentCert {
    /**
     * 主键ID
     */
    @PrimaryKey()
    @Column("`id`")
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 用户姓名
     */
    @Column("`user_name`")
    @ApiModelProperty("用户姓名")
    private String userName;

    /**
     * 用户性别(1：男，2：女)
     */
    @Column("`sex`")
    @ApiModelProperty("用户性别(1：男，2：女)")
    private Short sex;

    /**
     * 机构类型（1:私募,2:第三方机构,3:证券公司,4:银行,5:公募,6:保险公司,7:其他）
     */
    @Column("`org_type`")
    @ApiModelProperty("机构类型（1:私募,2:第三方机构,3:证券公司,4:银行,5:公募,6:保险公司,7:其他）")
    private Short orgType;

    /**
     * 机构名称
     */
    @Column("`org_name`")
    @ApiModelProperty("机构名称")
    private String orgName;

    /**
     * 职务
     */
    @Column("`position_name`")
    @ApiModelProperty("职务")
    private String positionName;

    /**
     * 从业年限（1：1年以内，2：1年到3年，3：3年到5年，4：5年到10年，5：10年以上）
     */
    @Column("`employment_year_num`")
    @ApiModelProperty("从业年限（1：1年以内，2：1年到3年，3：3年到5年，4：5年到10年，5：10年以上）")
    private Short employmentYearNum;

    /**
     * 个人名片图片url
     */
    @Column("`personal_card_img`")
    @ApiModelProperty("个人名片图片url")
    private String personalCardImg;

    /**
     * 资格证书图片url
     */
    @Column("`qualification_cert_img`")
    @ApiModelProperty("资格证书图片url")
    private String qualificationCertImg;

    /**
     * 认证状态(0：认证通过，1：审核中，2：认证失败)
     */
    @Column("`auth_status`")
    @ApiModelProperty("认证状态(0：认证通过，1：审核中，2：认证失败)")
    private Short authStatus;

    /**
     * 认证消息（记录失败原因）
     */
    @Column("`auth_msg`")
    @ApiModelProperty("认证消息（记录失败原因）")
    private String authMsg;

    /**
     * 创建时间
     */
    @Column("`create_date`")
    @ApiModelProperty("创建时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column("`update_date`")
    @ApiModelProperty("更新时间")
    private Date updateDate;
}
package com.rong.user.module.request;

import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUserEmploymentCert extends TqBase {

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String userName;

    /**
     * 用户性别(1：男，2：女)
     */
    @ApiModelProperty("用户性别(1：男，2：女)")
    private Short sex;

    /**
     * 机构类型（1:私募,2:第三方机构,3:证券公司,4:银行,5:公募,6:保险公司,7:其他）
     */
    @ApiModelProperty("机构类型（1:私募,2:第三方机构,3:证券公司,4:银行,5:公募,6:保险公司,7:其他）")
    private Short orgType;

    /**
     * 机构名称
     */
    @ApiModelProperty("机构名称")
    private String orgName;

    /**
     * 职务
     */
    @ApiModelProperty("职务")
    private String positionName;

    /**
     * 从业年限（1：1年以内，2：1年到3年，3：3年到5年，4：5年到10年，5：10年以上）
     */
    @ApiModelProperty("从业年限（1：1年以内，2：1年到3年，3：3年到5年，4：5年到10年，5：10年以上）")
    private Short employmentYearNum;

    /**
     * 个人名片图片url
     */
    @ApiModelProperty("个人名片图片url")
    private String personalCardImg;

    /**
     * 资格证书图片url
     */
    @ApiModelProperty("资格证书图片url")
    private String qualificationCertImg;

    /**
     * 认证状态(0：认证通过，1：审核中，2：认证失败)
     */
    @ApiModelProperty("认证状态(0：认证通过，1：审核中，2：认证失败)")
    private Short authStatus;

    /**
     * 认证消息（记录失败原因）
     */
    @ApiModelProperty("认证消息（记录失败原因）")
    private String authMsg;
}
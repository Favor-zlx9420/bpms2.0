package com.rong.assembly.api.module.request.usercard;

import com.rong.common.module.TqUserAuthBase;
import com.vitily.mybatis.core.annotation.Column;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 编辑名片信息
 */
@Data
public class TqAlterCardInfo extends TqUserAuthBase {

    /**
     * id
     */
    @ApiModelProperty("id，如果是添加，此项为空，如果是编辑，则必填")
    private Long id;

    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    private String headPortrait;

    /**
     * 姓
     */
    @ApiModelProperty(value = "姓",required = true)
    //@RequireValidator
    private String firstName;

    /**
     * 名
     */
    @Column("`last_name`")
    @ApiModelProperty(value = "名",required = true)
    //@RequireValidator
    private String lastName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码",required = true)
    //@RequireValidator
    private String phone;

    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址",required = true)
    //@RequireValidator
    private String email;

    /**
     * 微信号
     */
    @ApiModelProperty("微信号")
    private String wxNo;

    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    private String company;

    /**
     * 职位
     */
    @Column("`position`")
    @ApiModelProperty("职位")
    private String position;

    /**
     * 座机
     */
    @ApiModelProperty("座机")
    private String call;

    /**
     * 传真
     */
    @ApiModelProperty("传真")
    private String fax;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 家乡
     */
    @ApiModelProperty("家乡")
    private String hometown;

    /**
     * 个人简介
     */
    @ApiModelProperty("个人简介")
    private String personalIntroduction;

    /**
     * 公司简介
     */
    @ApiModelProperty("公司简介")
    private String companyIntroduction;

    /**
     * 团队简介
     */
    @ApiModelProperty("团队简介")
    private String teamIntroduction;

    /**
     * 荣耀
     */
    @ApiModelProperty("荣耀")
    private String glory;

    /**
     * 名片板式编号
     */
    @ApiModelProperty("名片板式编号，前端定义几个id，传到后台")
    private Long style;

    /**
     * 名片背景编号
     */
    @ApiModelProperty("名片背景编号，前端定义几个id，传到后台")
    private Long background;

    /**
     *对方申请交换名片需认证,(true:需认证，false：无需同意)
     */
    @ApiModelProperty("对方申请交换名片需认证,(true:需认证，false：无需同意)，默认 true，需认证")
    private Boolean swapVerify;

    /**
     * 名片联系方式公开可见,(true:公开可见，false：非公开可见)
     */
    @ApiModelProperty("名片联系方式公开可见,(true:公开可见，false：非公开可见)，默认 非公开可见false ")
    private Boolean contactPublicVisible;
}

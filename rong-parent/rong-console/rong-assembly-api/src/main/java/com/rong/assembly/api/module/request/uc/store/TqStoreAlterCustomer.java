package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqStoreAlterCustomer extends TqUserAuthBase {
    @ApiModelProperty(value = "客服用户id",required = true)
    @RequireValidator
    private Long customerUserId;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称",required = true)
    @RequireValidator
    private String nickname;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位",required = true)
    @RequireValidator
    private String position;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号",required = true)
    @RequireValidator
    private String phone;

    /**
     * 介绍信息
     */
    @ApiModelProperty(value = "介绍信息",required = true)
    @RequireValidator
    private String remark;

    /**
     * 自动回复信息
     */
    @ApiModelProperty("自动回复信息")
    private String autoReplay;

    /**
     * 头像图片地址
     */
    @ApiModelProperty("头像图片地址")
    private String headPortrait;

    /**
     * 从业资格证书1
     */
    @ApiModelProperty(value = "从业资格证书1",required = true)
    @RequireValidator
    private String certificate1Url;

    /**
     * 从业资格证书2
     */
    @ApiModelProperty("从业资格证书2")
    private String certificate2Url;

    /**
     * 从业资格证书3
     */
    @ApiModelProperty("从业资格证书3")
    private String certificate3Url;

    /**
     * 从业资格证书4
     */
    @ApiModelProperty("从业资格证书4")
    private String certificate4Url;

    /**
     * 从业资格证书5
     */
    @ApiModelProperty("从业资格证书5")
    private String certificate5Url;

    /**
     * 小程序二维码图片地址
     */
    @ApiModelProperty("小程序二维码图片地址")
    private String applicationCodeUrl;
}

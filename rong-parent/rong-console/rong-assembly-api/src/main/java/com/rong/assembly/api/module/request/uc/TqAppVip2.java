package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 申请金色vip
 */
@Data
public class TqAppVip2 extends TqUserAuthBase {

    /**
     * 机构全称
     */
    @ApiModelProperty(value = "机构全称",required = true)
    @RequireValidator
    private String partyFullName;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称",required = true)
    @RequireValidator
    private String departName;

    /**
     * 职位
     */
    @RequireValidator
    @ApiModelProperty(value = "职位",required = true)
    private String position;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 名片图片1
     */
    @ApiModelProperty("名片图片1")
    private String bizCardUrl1;

    /**
     * 名片图片2
     */
    @ApiModelProperty("名片图片2")
    private String bizCardUrl2;

    /**
     * 手持证件照1
     */
    @RequireValidator
    @ApiModelProperty(value = "手持证件照1",required = true)
    private String holdingPhotoUrl1;

    /**
     * 手持证件照2
     */
    @ApiModelProperty("手持证件照2")
    private String holdingPhotoUrl2;

    /**
     * 转账截图1
     */
    @RequireValidator
    @ApiModelProperty(value = "转账截图1",required = true)
    private String transferInfoUrl1;

    /**
     * 转账截图2
     */
    @ApiModelProperty("转账截图2")
    private String transferInfoUrl2;

    /**
     * 转账截图3
     */
    @ApiModelProperty("转账截图3")
    private String transferInfoUrl3;

    /**
     * 转账截图4
     */
    @ApiModelProperty("转账截图4")
    private String transferInfoUrl4;
}

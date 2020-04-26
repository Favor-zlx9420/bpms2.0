package com.rong.assembly.api.module.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("客服信息")
public class TrCustomerServer {
    @ApiModelProperty("客服用户id")
    private Long userId;
    @ApiModelProperty(value = "用户类型，取用户主表type",hidden = true)
    private Integer type;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("客服名字")
    private String userName;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像地址")
    private String headPortrait;
    @ApiModelProperty("在线状态（0:未在线，1：在线）")
    private Integer onlineState;
    @ApiModelProperty("服务用户人次")
    private int serviceNumCount;
    @ApiModelProperty("服务用户数")
    private int serviceUserCount;

    @ApiModelProperty(value = "用户标签id")
    private Long labelId;
    @ApiModelProperty("用户标签名字")
    private String labelName;
    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;
    @ApiModelProperty(value = "排序")
    private BigDecimal sort;
    @ApiModelProperty("推荐理由")
    private String labelReason;
    @ApiModelProperty("自定义标签0")
    private String labelVar0;
    @ApiModelProperty("自定义标签1")
    private String labelVar1;
    @ApiModelProperty("自定义标签2")
    private String labelVar2;
    @ApiModelProperty("自定义标签3")
    private String labelVar3;

}

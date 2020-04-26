package com.rong.assembly.api.module.response.people;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserVipInfo {
    @ApiModelProperty("vip等级，1：金色VIP，2：蓝色VIP")
    private Integer level;

    @ApiModelProperty("到期时间")
    private Date endDate;

    @ApiModelProperty("服务机构简称")
    private String partyShortName;

    @ApiModelProperty("服务机构id")
    private Long partyId;

    @ApiModelProperty("当前服务人次")
    private Integer serviceUserCount;

    @ApiModelProperty("已提交订单")
    private Integer orders;

    @ApiModelProperty("成功路演次数")
    private Integer userRoadshowCount;

    @ApiModelProperty("是否机构vip功能")
    private Boolean orgVip;

    @ApiModelProperty("是否驻店客服")
    private Boolean customer;

}

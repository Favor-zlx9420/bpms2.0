package com.rong.assembly.api.module.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("预约我的用户信息")
public class TrReservationUser {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户姓名")
    private String realName;
    @ApiModelProperty("处理状态：（0：未处理，1：已处理）")
    private int state;
    @ApiModelProperty("预约时间")
    private Date reservationDate;
}

package com.rong.store.module.view;

import com.rong.store.module.entity.TbDirectStoreUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TvDirectStoreUser extends TbDirectStoreUser {
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("用户类型")
    private Integer userType;
    @ApiModelProperty("服务用户人次")
    private int serviceNumCount;
    @ApiModelProperty("服务用户数")
    private int serviceUserCount;
    @ApiModelProperty("最后一次登录时间")
    private Date lastLoginDate;
    @ApiModelProperty("在线状态（0:未在线，1：在线）")
    private Integer onlineState;
    @ApiModelProperty("所服务机构简称")
    private String partyShortName;
    @ApiModelProperty("所服务机构简称")
    private String partyFullName;
}
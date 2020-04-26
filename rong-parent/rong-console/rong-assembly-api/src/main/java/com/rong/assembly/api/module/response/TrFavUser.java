package com.rong.assembly.api.module.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("关注我的用户信息")
public class TrFavUser {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户姓名")
    private String realName;
    @ApiModelProperty("`用户头像`")
    private String headPortrait;
}

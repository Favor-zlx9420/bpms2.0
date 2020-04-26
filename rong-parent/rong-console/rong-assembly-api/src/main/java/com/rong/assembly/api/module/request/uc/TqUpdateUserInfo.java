package com.rong.assembly.api.module.request.uc;

import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUpdateUserInfo extends TqUserAuthBase {
    @ApiModelProperty(value = "身份证")
    private String idNo;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "email")
    private String email;
    @ApiModelProperty(value = "用户密码：只有未设置过密码的才允许提交此项")
    private String password;
    @ApiModelProperty(value = "极光信息")
    private String jgInfo;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "用户头像地址")
    private String headPortrait;
    @ApiModelProperty(value = "通讯地址")
    private String address;
    @ApiModelProperty(value = "打招呼信息")
    private String greet;
}

package com.rong.assembly.api.module.request;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "找回密码请求实体",parent = TqBase.class)
public class TqResetPassword extends TqBase {
    private static final long serialVersionUID = 1L;
    @RequireValidator
    @ApiModelProperty(value = "接收验证码的手机号", name = "receiver", required = true, example = "13256985685",dataType = "string")
    private String receiver;
    @RequireValidator
    @ApiModelProperty(value = "手机验证码", name = "verificationCode", required = true, example = "5685",dataType = "string")
    private String verificationCode;
    @RequireValidator
    @ApiModelProperty(value = "新的登录密码", name = "password", required = true, example = "123456",dataType = "string")
    private String password;
}
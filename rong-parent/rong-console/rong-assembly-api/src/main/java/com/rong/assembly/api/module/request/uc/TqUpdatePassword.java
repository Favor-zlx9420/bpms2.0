package com.rong.assembly.api.module.request.uc;

import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUpdatePassword extends TqUserAuthBase {
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}

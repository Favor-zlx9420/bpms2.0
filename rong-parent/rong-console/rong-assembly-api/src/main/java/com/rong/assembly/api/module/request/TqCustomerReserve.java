package com.rong.assembly.api.module.request;

import com.rong.common.annotation.RegexValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import com.rong.common.util.Validator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqCustomerReserve extends TqBase {
    @ApiModelProperty(value = "用户姓名",required = true)
    @RequireValidator
    @RegexValidator(regexStr = Validator.STR_MAX_LENGTH64)
    private String name;

    @ApiModelProperty(value = "手机号",required = true)
    @RequireValidator
    @RegexValidator(regexStr = Validator.PHONE_NUMBER)
    private String phone;
}

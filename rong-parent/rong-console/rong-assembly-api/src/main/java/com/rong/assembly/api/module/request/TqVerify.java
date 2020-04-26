package com.rong.assembly.api.module.request;

import com.rong.common.annotation.IntegerRangeValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqVerify extends TqBase {
    /**
     * 业务类型 see @{CommonEnumContainer.TripartiteMessageContentType}
     */
    @RequireValidator
    @IntegerRangeValidator(min = 0,max = 100)
    @ApiModelProperty(value = "业务类型:1,注册;4:身份验证,5:找回密码;6:设置交易密码",allowableValues = "1,4,5,6",required = true)
    private Integer businessType;
}

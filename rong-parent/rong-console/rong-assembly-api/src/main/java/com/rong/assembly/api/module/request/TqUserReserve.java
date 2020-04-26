package com.rong.assembly.api.module.request;

import com.rong.common.annotation.RegexValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import com.rong.common.util.Validator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUserReserve extends TqBase {
    @ApiModelProperty(value = "用户姓名，假如用户登录，可以将手机号码传过来",required = true)
    @RequireValidator
    @RegexValidator(regexStr = Validator.STR_MAX_LENGTH64)
    private String name;

    @ApiModelProperty(value = "手机号，假如用户登录了可以将用户手机号码传过来",required = true,example = "13256554785")
    @RequireValidator
    @RegexValidator(regexStr = Validator.PHONE_NUMBER)
    private String phone;

    @ApiModelProperty(value = "预约类型（0：机构；1：基金经理；2：产品,3:路演）",required = true,allowableValues = "0,1,2,3")
    @RequireValidator
    private Integer type;

    @ApiModelProperty(value = "预约目标id，（机构则取partyId，基金经理则取personId，产品则取securityId）",required = true)
    @RequireValidator
    private Long targetId;

}

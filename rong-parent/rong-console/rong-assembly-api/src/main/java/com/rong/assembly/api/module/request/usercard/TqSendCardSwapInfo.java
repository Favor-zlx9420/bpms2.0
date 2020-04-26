package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqSendCardSwapInfo extends TqUserAuthBase {
    @ApiModelProperty(value = "对方名片id",required = true)
    @RequireValidator
    private Long theCardInfoId;
    @ApiModelProperty(value = "我的名片id",required = true)
    @RequireValidator
    private Long myCardInfoId;
}

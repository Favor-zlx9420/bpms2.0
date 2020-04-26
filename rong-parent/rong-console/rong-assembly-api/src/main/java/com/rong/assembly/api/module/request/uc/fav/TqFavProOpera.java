package com.rong.assembly.api.module.request.uc.fav;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqFavProOpera extends TqUserAuthBase {
    @ApiModelProperty(value = "产品id",required = true)
    @RequireValidator
    private Long securityId;
}

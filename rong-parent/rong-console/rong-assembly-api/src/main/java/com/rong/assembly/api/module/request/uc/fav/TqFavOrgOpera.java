package com.rong.assembly.api.module.request.uc.fav;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqFavOrgOpera extends TqUserAuthBase {
    @ApiModelProperty(value = "机构id",required = true)
    @RequireValidator
    private Long partyId;
}

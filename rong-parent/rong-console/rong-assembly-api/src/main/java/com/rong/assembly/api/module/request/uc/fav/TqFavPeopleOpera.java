package com.rong.assembly.api.module.request.uc.fav;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqFavPeopleOpera extends TqUserAuthBase {
    @ApiModelProperty(value = "机构人员id",required = true)
    @RequireValidator
    private Long personId;
}

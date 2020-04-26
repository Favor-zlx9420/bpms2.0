package com.rong.assembly.api.module.request.uc.fav;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqFavRoadShowOpera extends TqUserAuthBase {
    @ApiModelProperty(value = "路演id",required = true)
    @RequireValidator
    private Long roadShowId;
}

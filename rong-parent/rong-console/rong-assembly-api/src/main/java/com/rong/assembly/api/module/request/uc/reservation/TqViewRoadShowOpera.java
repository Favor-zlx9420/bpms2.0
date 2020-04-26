package com.rong.assembly.api.module.request.uc.reservation;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqViewRoadShowOpera extends TqUserAuthBase {
    @ApiModelProperty(value = "路演id",required = true)
    @RequireValidator
    private Long roadShowId;
}

package com.rong.assembly.api.module.request.uc.reservation;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUpdateReservation extends TqUserAuthBase {
    @ApiModelProperty(value = "预约id",required = true)
    @RequireValidator
    private Long id;
}

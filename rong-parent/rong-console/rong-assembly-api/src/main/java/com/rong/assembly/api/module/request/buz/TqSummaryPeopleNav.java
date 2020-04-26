package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqSummaryPeopleNav extends TqBase {

    @ApiModelProperty(value = "人员id", name = "personId", required = true)
    @RequireValidator
    private Long personId;
}

package com.rong.assembly.api.module.request;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqPeopleInfo extends TqBase {
    @ApiModelProperty(value = "人物id",required = true)
    @RequireValidator
    private Long personId;
}

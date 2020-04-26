package com.rong.assembly.api.module.request;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqOrgInfo extends TqBase {
    @ApiModelProperty(value = "机构id",required = true)
    @RequireValidator
    private Long partyId;
}

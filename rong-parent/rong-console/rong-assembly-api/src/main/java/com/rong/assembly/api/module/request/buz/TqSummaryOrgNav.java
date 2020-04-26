package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqSummaryOrgNav extends TqBase {

    @ApiModelProperty(value = "机构id", name = "partyId", required = true)
    @RequireValidator
    private Long partyId;
}

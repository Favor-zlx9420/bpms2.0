package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqFundsOfManager extends TqPageListBase {
    @ApiModelProperty(value = "人员id",required = true)
    @RequireValidator
    private Long personId;
    @ApiModelProperty(value = "类型，0：私募，2：公墓",required = true)
    private int type = CommonEnumContainer.ProductType.PRIVATE_PLACEMENT.getValue();
}

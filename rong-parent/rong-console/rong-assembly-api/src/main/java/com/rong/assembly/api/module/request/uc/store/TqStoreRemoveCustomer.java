package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqStoreRemoveCustomer extends TqUserAuthBase {
    @ApiModelProperty(value = "客服用户id",required = true)
    @RequireValidator
    private Long customerUserId;
}

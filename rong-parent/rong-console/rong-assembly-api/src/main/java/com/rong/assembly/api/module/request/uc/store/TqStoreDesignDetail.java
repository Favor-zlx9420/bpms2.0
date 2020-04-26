package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新增一条装潢记录
 */
@Data
public class TqStoreDesignDetail extends TqUserAuthBase {
    /**
     * id
     */
    @ApiModelProperty(value = "装潢店id",required = true)
    @RequireValidator
    private Long id;
}

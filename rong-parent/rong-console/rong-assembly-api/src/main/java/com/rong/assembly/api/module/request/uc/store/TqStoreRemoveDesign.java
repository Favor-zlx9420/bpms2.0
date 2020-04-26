package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 新增一条装潢记录
 */
@Data
public class TqStoreRemoveDesign extends TqUserAuthBase {
    /**
     * ids
     */
    @ApiModelProperty(value = "装潢店id组合",required = true)
    @RequireValidator
    private List<Long> ids;
}

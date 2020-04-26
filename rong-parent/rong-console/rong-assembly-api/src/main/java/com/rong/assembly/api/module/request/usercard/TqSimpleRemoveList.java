package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TqSimpleRemoveList extends TqUserAuthBase {
    @ApiModelProperty(value = "说说id",required = true)
    @RequireValidator
    private List<Long> ids;
}

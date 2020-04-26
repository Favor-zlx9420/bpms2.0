package com.rong.assembly.api.module.request.usercard;

import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqCardModuleDetail extends TqUserAuthBase {
    @ApiModelProperty("模块id")
    private Long id;
}

package com.rong.assembly.api.module.request.buz;

import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqRoadShowLabels extends TqBase {
    @ApiModelProperty("路演分类id")
    private Long cateId;
}

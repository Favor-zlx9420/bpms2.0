package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.SqlCondition;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqRoadShowDetail extends TqBase {
    @ApiModelProperty("路演id")
    @SqlCondition("e.id.eq")
    private Long id;
}

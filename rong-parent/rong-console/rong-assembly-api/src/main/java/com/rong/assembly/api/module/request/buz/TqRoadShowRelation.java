package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.annotation.SqlCondition;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("相关路演")
public class TqRoadShowRelation extends TqBase {
    @ApiModelProperty(value = "路演id",required = true)
    @SqlCondition("e.id.eq")
    @RequireValidator
    private Long id;
}

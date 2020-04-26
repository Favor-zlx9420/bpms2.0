package com.rong.assembly.api.module.request.usercard;

import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class TqUserCardSwapInfo extends TqBase {
    @ApiParam("处理结果（0：未处理，1：已同意，2：目标拒绝）,默认未处理")
    private Integer dealResult = 0;
}

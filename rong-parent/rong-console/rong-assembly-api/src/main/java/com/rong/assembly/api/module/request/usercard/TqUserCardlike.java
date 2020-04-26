package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUserCardlike extends TqUserAuthBase {
    @ApiModelProperty(value = "名片id",required = true)
    @RequireValidator
    private Long cardInfoId;
}

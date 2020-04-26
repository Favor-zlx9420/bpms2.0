package com.rong.assembly.api.module.request;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqGetLabelList extends TqPageListBase {
    @ApiModelProperty(value = "0:商品标签,4:路演标签,8:直营店产品标签,9:直营店标签",required = true)
    @RequireValidator
    private Integer labelType;
}

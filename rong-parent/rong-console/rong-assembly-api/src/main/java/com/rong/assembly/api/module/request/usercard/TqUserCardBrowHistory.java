package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUserCardBrowHistory extends TqPageListBase {
    @ApiModelProperty(value = "名片id",required = true)
    @RequireValidator
    private Long id;
    @ApiModelProperty(value = "搜索关键字")
    private String keyword;
}

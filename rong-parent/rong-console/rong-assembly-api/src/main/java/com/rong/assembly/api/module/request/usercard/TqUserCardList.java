package com.rong.assembly.api.module.request.usercard;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUserCardList extends TqMySimpleList {
    @ApiModelProperty(value = "搜索关键字")
    private String keyword;
}

package com.rong.assembly.api.module.request.usercard;

import com.rong.common.module.TqUserAuthPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqMySimpleList extends TqUserAuthPageListBase {
    @ApiModelProperty(value = "是否删除,默认不删除",allowableValues = "true,false",example = "false")
    private Boolean deltag = false;
    @ApiModelProperty(value = "搜索关键字")
    private String keyword;
}

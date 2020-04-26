package com.rong.assembly.api.module.response.store;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TrStoreDesign implements Serializable {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("副标标题")
    private String subTitle;
    @ApiModelProperty("内容")
    private String content;
}

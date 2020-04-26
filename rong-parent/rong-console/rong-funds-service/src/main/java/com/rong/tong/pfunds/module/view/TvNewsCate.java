package com.rong.tong.pfunds.module.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvNewsCate {

    @ApiModelProperty(value = "栏目id")
    private Integer cateId;

    @ApiModelProperty(value = "名称")
    private String name;
}
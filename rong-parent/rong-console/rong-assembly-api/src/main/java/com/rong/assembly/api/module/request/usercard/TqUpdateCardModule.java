package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TqUpdateCardModule extends TqUserAuthBase {
    @ApiModelProperty(value = "模块标题",required = true)
    private String title;
    @ApiModelProperty("模块内容")
    private String content;
    @ApiModelProperty(value = "模块id",required = true)
    @RequireValidator
    private Long id;
    @ApiModelProperty("排序")
    private BigDecimal sort;
    @ApiModelProperty("删除位：0：未删除，1：已删除")
    private Boolean deltag;
}

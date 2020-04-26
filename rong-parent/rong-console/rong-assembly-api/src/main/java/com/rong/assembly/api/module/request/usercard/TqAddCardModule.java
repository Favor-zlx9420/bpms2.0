package com.rong.assembly.api.module.request.usercard;

import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqAddCardModule extends TqUserAuthBase {
    @ApiModelProperty("模块标题")
    private String title;
    @ApiModelProperty("模块内容")
    private String content;
}

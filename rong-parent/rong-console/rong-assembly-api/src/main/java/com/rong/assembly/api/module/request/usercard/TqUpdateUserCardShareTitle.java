package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUpdateUserCardShareTitle extends TqUserAuthBase {
    @ApiModelProperty(value = "分享标题内容",required = true)
    @RequireValidator
    private String title;
    @ApiModelProperty(value = "id",required = true)
    @RequireValidator
    private Long id;
    @ApiModelProperty("状态（0：未使用，1：使用中）")
    private Integer state;
}

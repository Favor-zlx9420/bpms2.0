package com.rong.assembly.api.module.request;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqGetCmsNews extends TqPageListBase {
    @ApiModelProperty(value = "分类id，可登录后台查看", example = "23")
    private Integer cateId;
    @ApiModelProperty(value = "类型id，可登录后台查看")
    private Integer typeId;

    @ApiModelProperty(value = "用户ID")
    private long userId;
}

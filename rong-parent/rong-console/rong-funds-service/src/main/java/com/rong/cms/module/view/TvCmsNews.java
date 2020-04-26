package com.rong.cms.module.view;

import com.rong.cms.module.entity.TbCmsNews;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvCmsNews extends TbCmsNews {
    @ApiModelProperty("类型名称")
    private String typeName;
    @ApiModelProperty("分类名称")
    private String cateName;
    @ApiModelProperty("发表人")
    private String createName;
}
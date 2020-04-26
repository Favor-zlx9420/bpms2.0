package com.rong.fundmanage.module.view;

import com.rong.fundmanage.module.entity.TbProductLabel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvProductLabel extends TbProductLabel {
    @ApiModelProperty("基金简称")
    private String secShortName;
    @ApiModelProperty("基金全名")
    private String secFullName;
}
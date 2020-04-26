package com.rong.tong.pfunds.module.request;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TqNewsList extends TqPageListBase {

    @ApiModelProperty(value = "搜索词", name = "key")
    private String key;

    @ApiModelProperty(value = "栏目id", name = "cateId")
    private Integer cateId;

    @ApiModelProperty(hidden = true)
    private Integer limitStart;

    @ApiModelProperty(hidden = true)
    private Integer limitEnd;

    @ApiModelProperty(hidden = true)
    private Date startDate;
}
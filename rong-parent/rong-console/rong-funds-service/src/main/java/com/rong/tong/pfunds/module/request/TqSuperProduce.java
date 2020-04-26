package com.rong.tong.pfunds.module.request;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqSuperProduce extends TqPageListBase {

    @ApiModelProperty(value = "投资策略(1股票策略2相对价值3宏观策略4事件驱动5组合基金6债券策略7管理期货8复合策略9其他策略,多个用,号隔开)", name = "investStrategy")
    private String investStrategy;

    @ApiModelProperty(hidden = true)
    private Integer limitStart;

    @ApiModelProperty(hidden = true)
    private Integer limitEnd;

    @ApiModelProperty(value = "13:在售优品(优选基金),34:热门基金,35为你推荐", name = "labelId", required = true)
    private Integer labelId;

}
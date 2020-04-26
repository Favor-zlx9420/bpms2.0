package com.rong.product.module.view;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Package: com.rong.product.module.view
 * @Author: LQW
 * @Date: 2020/4/22
 * @Description:收益曲线图
 */
public class TvYieldCurve {

    @ApiModelProperty("同类表现")
    private BigDecimal similarPerformance;

    @ApiModelProperty("沪深300")
    private BigDecimal csi300;

    @ApiModelProperty("自身")
    private BigDecimal itself;

}

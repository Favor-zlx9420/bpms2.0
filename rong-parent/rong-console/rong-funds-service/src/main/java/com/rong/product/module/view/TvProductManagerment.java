package com.rong.product.module.view;

import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Package: com.rong.product.module.view
 * @Author: LQW
 * @Date: 2020/4/21
 * @Description:产品管理试图对象
 */
@Data
public class TvProductManagerment extends TqBase {

    @ApiModelProperty(value = "证券内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "近一年年化收益")
    private String annualizedReturns;

    @ApiModelProperty(value = "起投金额")
    private String initialDeliveryAmount;

    @ApiModelProperty(value = "存续期限")
    private String term;

    //TODO 这边暂时先写死状态，后期跟乙方接口对接时，再跟进
    @ApiModelProperty(value = "审核状态")
    private Integer auditStatus;

    @ApiModelProperty(value = "最新净值")
    private BigDecimal theLatestNetValue;

    @ApiModelProperty(value = "基金经理")
    private String fundManager;

    @ApiModelProperty(value = "基金经理id")
    private String fundManagerId;

    @ApiModelProperty(value = "最新净值日期")
    private String theLatestNetValueDate;

    @ApiModelProperty(value = "累计净值")
    private String theCumulativeNetValue;

    /**
     *  日涨幅暂时赋值初始值
     */
    @ApiModelProperty("日涨幅")
    private String dailyIncreasesDegrees = "-";

    @ApiModelProperty(value = "基金类型/基金策略")
    private String theFundType;
    //region  收益曲线  --- 因业务需求的原因，这边暂时不提供数据

    @ApiModelProperty(value = "成立以来")
    private List<TvYieldCurve> sinceTheFoundingOf;

    @ApiModelProperty(value = "近半年")
    private List<TvYieldCurve> nearlyHalfaYear;

    @ApiModelProperty(value = "近3月")
    private List<TvYieldCurve> forNearlyThreeMonths;

    @ApiModelProperty(value = "近1月")
    private List<TvYieldCurve> nearlyaMonth;

    //endregion

    /**
     * 产品类型  1：（私募）2：（公募）
     */
    @ApiModelProperty("产品类型")
    private Integer productType;

}

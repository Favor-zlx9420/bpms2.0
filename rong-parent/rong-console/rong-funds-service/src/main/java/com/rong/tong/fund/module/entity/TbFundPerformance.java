package com.rong.tong.fund.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : lether
 * @createDate  : 2020-02-21
 */
@Table("`tong-rong`.`fund_performance`")
@Data()
@Accessors(chain = true)
public class TbFundPerformance {
    /**
     * 自增ID
     */
    @Column("`ID`")
    @ApiModelProperty("自增ID")
    private Long id;

    /**
     * 基金内部编码
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("基金内部编码")
    private Long securityId;

    /**
     * 截至日期
     */
    @Column("`END_DATE`")
    @ApiModelProperty("截至日期")
    private Date endDate;

    /**
     * 周回报率
     */
    @Column("`RETURN_RATE_1W`")
    @ApiModelProperty("周回报率")
    private BigDecimal returnRate1w;

    /**
     * 近一年累计收益率
     */
    @Column("`RETURN_RATE_1Y`")
    @ApiModelProperty("近一年累计收益率")
    private BigDecimal returnRate1y;

    /**
     * 周平均收益率
     */
    @Column("`AVERAGE_RETURN_RATE`")
    @ApiModelProperty("周平均收益率")
    private BigDecimal averageReturnRate;

    /**
     * 风险
     */
    @Column("`STDEV`")
    @ApiModelProperty("风险")
    private BigDecimal stdev;

    /**
     * 夏普比率
     */
    @Column("`SHARPE_RATIO`")
    @ApiModelProperty("夏普比率")
    private BigDecimal sharpeRatio;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    @ApiModelProperty("")
    private Long tmstamp;
}
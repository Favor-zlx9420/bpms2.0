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
@Table("`tong-rong`.`fund_mperf`")
@Data()
@Accessors(chain = true)
public class TbFundMperf {
    /**
     * 信息编码
     */
    @Column("`ID`")
    @ApiModelProperty("信息编码")
    private Long id;

    /**
     * 基金ID
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("基金ID")
    private Long securityId;

    /**
     * 截止日期
     */
    @Column("`END_DATE`")
    @ApiModelProperty("截止日期")
    private Date endDate;

    /**
     * 区间范围
     */
    @Column("`WINDOW`")
    @ApiModelProperty("区间范围")
    private Integer window;

    /**
     * 区间万份收益均值
     */
    @Column("`AVG_DAILY_PROFIT`")
    @ApiModelProperty("区间万份收益均值")
    private BigDecimal avgDailyProfit;

    /**
     * 区间万份收益方差
     */
    @Column("`VAR_DAILY_PROFIT`")
    @ApiModelProperty("区间万份收益方差")
    private BigDecimal varDailyProfit;

    /**
     * 区间7日年化收益率均值
     */
    @Column("`AVG_WEEKLY_YIELD`")
    @ApiModelProperty("区间7日年化收益率均值")
    private BigDecimal avgWeeklyYield;

    /**
     * 区间7日年化收益率方差
     */
    @Column("`VAR_WEEKLY_YIELD`")
    @ApiModelProperty("区间7日年化收益率方差")
    private BigDecimal varWeeklyYield;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 时间戳
     */
    @Column("`TMSTAMP`")
    @ApiModelProperty("时间戳")
    private Long tmstamp;
}
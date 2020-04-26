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
@Table("`tong-rong`.`fund_divm`")
@Data()
@Accessors(chain = true)
public class TbFundDivm {
    /**
     * 自增ID
     */
    @Column("`ID`")
    @ApiModelProperty("自增ID")
    private Long id;

    /**
     * 分级基金编码
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("分级基金编码")
    private Long securityId;

    /**
     * 发布日期
     */
    @Column("`PUBLISH_DATE`")
    @ApiModelProperty("发布日期")
    private Date publishDate;

    /**
     * 收益日期
     */
    @Column("`END_DATE`")
    @ApiModelProperty("收益日期")
    private Date endDate;

    /**
     * 币种
     */
    @Column("`CURRENCY_CD`")
    @ApiModelProperty("币种")
    private String currencyCd;

    /**
     * 每万份基金单位当日收益(元)
     */
    @Column("`DAILY_PROFIT`")
    @ApiModelProperty("每万份基金单位当日收益(元)")
    private BigDecimal dailyProfit;

    /**
     * 7日年化收益率
     */
    @Column("`WEEKLY_YIELD`")
    @ApiModelProperty("7日年化收益率")
    private BigDecimal weeklyYield;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 
     */
    @Column("`NAV`")
    @ApiModelProperty("")
    private BigDecimal nav;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    @ApiModelProperty("")
    private Long tmstamp;
}
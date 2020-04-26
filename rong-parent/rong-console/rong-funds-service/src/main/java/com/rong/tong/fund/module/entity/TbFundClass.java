package com.rong.tong.fund.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : lether
 * @createDate  : 2020-02-21
 */
@Table("`tong-rong`.`fund_class`")
@Data()
@Accessors(chain = true)
public class TbFundClass {
    /**
     * 自动增长ID
     */
    @Column("`ID`")
    @ApiModelProperty("自动增长ID")
    private Long id;

    /**
     * 基金主编码
     */
    @Column("`FUND_ID`")
    @ApiModelProperty("基金主编码")
    private Long fundId;

    /**
     * 基金内部编码
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("基金内部编码")
    private Long securityId;

    /**
     * 分级名称
     */
    @Column("`CLASS_NAME`")
    @ApiModelProperty("分级名称")
    private String className;

    /**
     * 基金代码
     */
    @Column("`TICKER_SYMBOL`")
    @ApiModelProperty("基金代码")
    private String tickerSymbol;

    /**
     * 交易所代码
     */
    @Column("`EXCHANGE_CD`")
    @ApiModelProperty("交易所代码")
    private String exchangeCd;

    /**
     * 分级状态
     */
    @Column("`CLASS_STATUS`")
    @ApiModelProperty("分级状态")
    private String classStatus;

    /**
     * 存续期截止日
     */
    @Column("`EXPIRE_DATE`")
    @ApiModelProperty("存续期截止日")
    private Date expireDate;

    /**
     * 运作方式
     */
    @Column("`OPERATION_MODE`")
    @ApiModelProperty("运作方式")
    private String operationMode;

    /**
     * ETF或LOF
     */
    @Column("`ETF_LOF`")
    @ApiModelProperty("ETF或LOF")
    private String etfLof;

    /**
     * 保本比例(%)
     */
    @Column("`GUAR_RATIO`")
    @ApiModelProperty("保本比例(%)")
    private Long guarRatio;

    /**
     * 保本期限(月)
     */
    @Column("`GUAR_PERIOD`")
    @ApiModelProperty("保本期限(月)")
    private Long guarPeriod;

    /**
     * 是否保本型基金0否；1是
     */
    @Column("`IS_GUAR_FUND`")
    @ApiModelProperty("是否保本型基金0否；1是")
    private Boolean isGuarFund;

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

    /**
     * 
     */
    @Column("`IS_CLASS`")
    @ApiModelProperty("")
    private Integer isClass;

    /**
     * 
     */
    @Column("`CLEAR_TYPE`")
    @ApiModelProperty("")
    private Integer clearType;

    /**
     * 成立日期
     */
    @Column("`ESTABLISH_DATE`")
    @ApiModelProperty("成立日期")
    private Date establishDate;

    /**
     * 前端申购代码
     */
    @Column("`TICKER_SYMBOL_F`")
    @ApiModelProperty("前端申购代码")
    private String tickerSymbolF;

    /**
     * 后端申购代码
     */
    @Column("`TICKER_SYMBOL_B`")
    @ApiModelProperty("后端申购代码")
    private String tickerSymbolB;

    /**
     * 认购代码
     */
    @Column("`TICKER_SYMBOL_SUB`")
    @ApiModelProperty("认购代码")
    private String tickerSymbolSub;

    /**
     * 一级市场基金代码
     */
    @Column("`TICKER_SYMBOL_PM`")
    @ApiModelProperty("一级市场基金代码")
    private String tickerSymbolPm;

    /**
     * 基金主代码
     */
    @Column("`TICKER_SYMBOL_M`")
    @ApiModelProperty("基金主代码")
    private String tickerSymbolM;

    /**
     * 场内简称
     */
    @Column("`SEC_SHORT_NAME_EX`")
    @ApiModelProperty("场内简称")
    private String secShortNameEx;

    /**
     * 场内申赎代码
     */
    @Column("`TICKER_SYMBOL_EX`")
    @ApiModelProperty("场内申赎代码")
    private String tickerSymbolEx;
}
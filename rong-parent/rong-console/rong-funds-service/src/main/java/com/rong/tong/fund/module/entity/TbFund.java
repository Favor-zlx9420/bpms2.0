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
@Table("`tong-rong`.`fund`")
@Data()
@Accessors(chain = true)
public class TbFund {
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
     * 基金名称
     */
    @Column("`SEC_FULL_NAME`")
    @ApiModelProperty("基金名称")
    private String secFullName;

    /**
     * 基金简称
     */
    @Column("`SEC_SHORT_NAME`")
    @ApiModelProperty("基金简称")
    private String secShortName;

    /**
     * 基金管理人
     */
    @Column("`MANAGEMENT_COMPANY`")
    @ApiModelProperty("基金管理人")
    private Long managementCompany;

    /**
     * 基金托管人
     */
    @Column("`CUSTODIAN`")
    @ApiModelProperty("基金托管人")
    private Long custodian;

    /**
     * 投资顾问
     */
    @Column("`INVESTMENT_ADVISER`")
    @ApiModelProperty("投资顾问")
    private Long investmentAdviser;

    /**
     * 基金管理方式
     */
    @Column("`MANAGEMENT_MODE`")
    @ApiModelProperty("基金管理方式")
    private String managementMode;

    /**
     * 基金类型
     */
    @Column("`CATEGORY`")
    @ApiModelProperty("基金类型")
    private String category;

    /**
     * 是否指数型
     */
    @Column("`INDEX_FUND`")
    @ApiModelProperty("是否指数型")
    private String indexFund;

    /**
     * ETF或LOF
     */
    @Column("`ETF_LOF`")
    @ApiModelProperty("ETF或LOF")
    private String etfLof;

    /**
     * 是否QDII
     */
    @Column("`IS_QDII`")
    @ApiModelProperty("是否QDII")
    private Boolean isQdii;

    /**
     * 是否FOF
     */
    @Column("`IS_FOF`")
    @ApiModelProperty("是否FOF")
    private Boolean isFof;

    /**
     * 投资目标
     */
    @Column("`INVEST_TARGET`")
    @ApiModelProperty("投资目标")
    private String investTarget;

    /**
     * 业绩比较基准
     */
    @Column("`PERF_BENCHMARK`")
    @ApiModelProperty("业绩比较基准")
    private String perfBenchmark;

    /**
     * 注册地
     */
    @Column("`REG_PLACE`")
    @ApiModelProperty("注册地")
    private String regPlace;

    /**
     * 成立日期
     */
    @Column("`ESTABLISH_DATE`")
    @ApiModelProperty("成立日期")
    private Date establishDate;

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
    @Column("`PERF_BENCHMARK_EN`")
    @ApiModelProperty("")
    private String perfBenchmarkEn;

    /**
     * 投资范围
     */
    @Column("`INVEST_FIELD`")
    @ApiModelProperty("投资范围")
    private String investField;
}
package com.rong.fundmanage.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 当前净值回报
 * @author      : Administrator
 * @createDate  : 2020-02-25
 */
@Table("`tb_raised_fund_current_nav_gr`")
@Data()
@Accessors(chain = true)
public class TbRaisedFundCurrentNavGr extends BaseEntity<TbRaisedFundCurrentNavGr> {
    /**
     * 内部id
     */
    @Column("`security_id`")
    @ApiModelProperty("内部id")
    private Long securityId;

    /**
     * 截止日期
     */
    @Column("`end_date`")
    @ApiModelProperty("截止日期")
    private Date endDate;

    /**
     * 最小区间
     */
    @Column("`return_rate`")
    @ApiModelProperty("最小区间")
    private BigDecimal returnRate;

    /**
     * 本周以来
     */
    @Column("`return_rate_wtd`")
    @ApiModelProperty("本周以来")
    private BigDecimal returnRateWtd;

    /**
     * 近一周
     */
    @Column("`return_rate_1w`")
    @ApiModelProperty("近一周")
    private BigDecimal returnRate1w;

    /**
     * 本月以来
     */
    @Column("`return_rate_mtd`")
    @ApiModelProperty("本月以来")
    private BigDecimal returnRateMtd;

    /**
     * 近一月
     */
    @Column("`return_rate_1m`")
    @ApiModelProperty("近一月")
    private BigDecimal returnRate1m;

    /**
     * 近三月
     */
    @Column("`return_rate_3m`")
    @ApiModelProperty("近三月")
    private BigDecimal returnRate3m;

    /**
     * 近六月
     */
    @Column("`return_rate_6m`")
    @ApiModelProperty("近六月")
    private BigDecimal returnRate6m;

    /**
     * 今年以来
     */
    @Column("`return_rate_ytd`")
    @ApiModelProperty("今年以来")
    private BigDecimal returnRateYtd;

    /**
     * 近一年
     */
    @Column("`return_rate_1y`")
    @ApiModelProperty("近一年")
    private BigDecimal returnRate1y;

    /**
     * 近二年
     */
    @Column("`return_rate_2y`")
    @ApiModelProperty("近二年")
    private BigDecimal returnRate2y;

    /**
     * 近三年
     */
    @Column("`return_rate_3y`")
    @ApiModelProperty("近三年")
    private BigDecimal returnRate3y;

    /**
     * 近五年
     */
    @Column("`return_rate_5y`")
    @ApiModelProperty("近五年")
    private BigDecimal returnRate5y;

    /**
     * 成立以来
     */
    @Column("`return_rate_est`")
    @ApiModelProperty("成立以来")
    private BigDecimal returnRateEst;

    /**
     * 
     */
    @Column("`person_id`")
    @ApiModelProperty("")
    private Long personId;

    /**
     * 
     */
    @Column("`party_id`")
    @ApiModelProperty("")
    private Long partyId;
}
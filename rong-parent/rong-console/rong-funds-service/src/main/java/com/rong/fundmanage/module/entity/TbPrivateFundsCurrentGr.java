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
 * @description : 私募基金当前净值增长
 * @author      : ludexin
 * @createDate  : 2020-02-18
 */
@Table("`tb_private_funds_current_gr`")
@Data()
@Accessors(chain = true)
public class TbPrivateFundsCurrentGr extends BaseEntity<TbPrivateFundsCurrentGr> {
    /**
     * 内部ID
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("内部ID")
    private Long securityId;

    /**
     * 截止日期
     */
    @Column("`END_DATE`")
    @ApiModelProperty("截止日期")
    private Date endDate;

    /**
     * 最小区间
     */
    @Column("`RETURN_RATE`")
    @ApiModelProperty("最小区间")
    private BigDecimal returnRate;

    /**
     * 本周以来
     */
    @Column("`RETURN_RATE_WTD`")
    @ApiModelProperty("本周以来")
    private BigDecimal returnRateWtd;

    /**
     * 近一周
     */
    @Column("`RETURN_RATE_1W`")
    @ApiModelProperty("近一周")
    private BigDecimal returnRate1w;

    /**
     * 本月以来
     */
    @Column("`RETURN_RATE_MTD`")
    @ApiModelProperty("本月以来")
    private BigDecimal returnRateMtd;

    /**
     * 近一月
     */
    @Column("`RETURN_RATE_1M`")
    @ApiModelProperty("近一月")
    private BigDecimal returnRate1m;

    /**
     * 近三月
     */
    @Column("`RETURN_RATE_3M`")
    @ApiModelProperty("近三月")
    private BigDecimal returnRate3m;

    /**
     * 近六月
     */
    @Column("`RETURN_RATE_6M`")
    @ApiModelProperty("近六月")
    private BigDecimal returnRate6m;

    /**
     * 今年以来
     */
    @Column("`RETURN_RATE_YTD`")
    @ApiModelProperty("今年以来")
    private BigDecimal returnRateYtd;

    /**
     * 近一年
     */
    @Column("`RETURN_RATE_1Y`")
    @ApiModelProperty("近一年")
    private BigDecimal returnRate1y;

    /**
     * 近二年
     */
    @Column("`RETURN_RATE_2Y`")
    @ApiModelProperty("近二年")
    private BigDecimal returnRate2y;

    /**
     * 近三年
     */
    @Column("`RETURN_RATE_3Y`")
    @ApiModelProperty("近三年")
    private BigDecimal returnRate3y;

    /**
     * 近五年
     */
    @Column("`RETURN_RATE_5Y`")
    @ApiModelProperty("近五年")
    private BigDecimal returnRate5y;

    /**
     * 成立以来
     */
    @Column("`RETURN_RATE_EST`")
    @ApiModelProperty("成立以来")
    private BigDecimal returnRateEst;

    /**
     * 是否年度业绩
     */
    @Column("`IS_YEAR`")
    @ApiModelProperty("是否年度业绩")
    private Boolean isYear;

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

    /**
     * 基金经理id
     */
    @Column("`person_id`")
    @ApiModelProperty("基金经理id")
    private Long personId;

    /**
     * 机构id
     */
    @Column("`party_id`")
    @ApiModelProperty("机构id")
    private Long partyId;
}
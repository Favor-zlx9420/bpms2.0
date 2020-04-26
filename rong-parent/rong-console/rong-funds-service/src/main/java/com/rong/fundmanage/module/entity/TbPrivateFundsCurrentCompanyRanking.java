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
 * @description : 基金公司排行表
 * @author      : Administrator
 * @createDate  : 2020-03-01
 */
@Table("`tb_private_funds_current_company_ranking`")
@Data()
@Accessors(chain = true)
public class TbPrivateFundsCurrentCompanyRanking extends BaseEntity<TbPrivateFundsCurrentCompanyRanking> {
    /**
     * 机构内部编码
     */
    @Column("`PARTY_ID`")
    @ApiModelProperty("机构内部编码")
    private Long partyId;

    /**
     * 公司规模
     */
    @Column("`SCALE`")
    @ApiModelProperty("公司规模")
    private String scale;

    /**
     * 管理基金主要类别
     */
    @Column("`MAIN_FUND_TYPE`")
    @ApiModelProperty("管理基金主要类别")
    private String mainFundType;

    /**
     * 所在地区
     */
    @Column("`REG_CITY`")
    @ApiModelProperty("所在地区")
    private String regCity;

    /**
     * 基金公司简称
     */
    @Column("`PARTY_SHORT_NAME`")
    @ApiModelProperty("基金公司简称")
    private String partyShortName;

    /**
     * 基金公司全称
     */
    @Column("`PARTY_FULL_NAME`")
    @ApiModelProperty("基金公司全称")
    private String partyFullName;

    /**
     * 管理产品总数
     */
    @Column("`NUM_ALL`")
    @ApiModelProperty("管理产品总数")
    private Integer numAll;

    /**
     * 近一月夏普比率
     */
    @Column("`SHARPE_RATIO_1M`")
    @ApiModelProperty("近一月夏普比率")
    private BigDecimal sharpeRatio1m;

    /**
     * 近三月夏普比率
     */
    @Column("`SHARPE_RATIO_3M`")
    @ApiModelProperty("近三月夏普比率")
    private BigDecimal sharpeRatio3m;

    /**
     * 近六月夏普比率
     */
    @Column("`SHARPE_RATIO_6M`")
    @ApiModelProperty("近六月夏普比率")
    private BigDecimal sharpeRatio6m;

    /**
     * 近一年夏普比率
     */
    @Column("`SHARPE_RATIO_1Y`")
    @ApiModelProperty("近一年夏普比率")
    private BigDecimal sharpeRatio1y;

    /**
     * 近二年夏普比率
     */
    @Column("`SHARPE_RATIO_2Y`")
    @ApiModelProperty("近二年夏普比率")
    private BigDecimal sharpeRatio2y;

    /**
     * 近三年夏普比率
     */
    @Column("`SHARPE_RATIO_3Y`")
    @ApiModelProperty("近三年夏普比率")
    private BigDecimal sharpeRatio3y;

    /**
     * 成立以来夏普比率
     */
    @Column("`SHARPE_RATIO_EST`")
    @ApiModelProperty("成立以来夏普比率")
    private BigDecimal sharpeRatioEst;

    /**
     * 今年以来夏普比率
     */
    @Column("`SHARPE_RATIO_YTD`")
    @ApiModelProperty("今年以来夏普比率")
    private BigDecimal sharpeRatioYtd;

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
     * 今年以来
     */
    @Column("`RETURN_RATE_YTD`")
    @ApiModelProperty("今年以来")
    private BigDecimal returnRateYtd;

    /**
     * 代表产品内部ID
     */
    @Column("`SECURITY_ID`")
    @ApiModelProperty("代表产品内部ID")
    private Long securityId;

    /**
     * 代表产品简称
     */
    @Column("`SEC_SHORT_NAME`")
    @ApiModelProperty("代表产品简称")
    private String secShortName;

    /**
     * 代表产品全称
     */
    @Column("`SEC_FULL_NAME`")
    @ApiModelProperty("代表产品全称")
    private String secFullName;

    /**
     * 代表产品最近净值
     */
    @Column("`NAV`")
    @ApiModelProperty("代表产品最近净值")
    private Long nav;

    /**
     * 净值日期
     */
    @Column("`END_DATE`")
    @ApiModelProperty("净值日期")
    private Date endDate;

    /**
     * 代表产品累计收益
     */
    @Column("`RETURN_ACCUM`")
    @ApiModelProperty("代表产品累计收益")
    private Long returnAccum;

    /**
     * 核心人物
     */
    @Column("`KEY_PERSON`")
    @ApiModelProperty("核心人物")
    private String keyPerson;
}
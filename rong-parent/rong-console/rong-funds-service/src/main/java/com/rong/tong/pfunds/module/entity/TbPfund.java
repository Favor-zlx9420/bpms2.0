package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-02-03
 */
@Table("`tong-rong`.`pfund`")
@Data()
@Accessors(chain = true)
public class TbPfund {
    /**
     * 
     */
    @Column("`ID`")
    private Long id;

    /**
     * 
     */
    @Column("`SECURITY_ID`")
    private Long securityId;

    /**
     * 
     */
    @Column("`ESTABLISH_DATE`")
    @ApiModelProperty("成立日期")
    private Date establishDate;

    /**
     * 
     */
    @Column("`PF_STYLE`")
    private String pfStyle;

    /**
     * 
     */
    @Column("`STATUS`")
    private String status;

    /**
     * 
     */
    @Column("`INVEST_STRATEGY`")
    private String investStrategy;

    /**
     * 
     */
    @Column("`INVEST_STRATEGY_CHILD`")
    private String investStrategyChild;

    /**
     * 
     */
    @Column("`DURATION`")
    private Integer duration;

    /**
     * 
     */
    @Column("`OPEN_DATE_DESC`")
    private String openDateDesc;

    /**
     * 
     */
    @Column("`INVEST_CONSULTANT`")
    private String investConsultant;

    /**
     * 
     */
    @Column("`CUSTODIAN`")
    private String custodian;

    /**
     * 
     */
    @Column("`ISSUE_PLATFORM`")
    private String issuePlatform;

    /**
     * 
     */
    @Column("`TRADING_BROKER`")
    private String tradingBroker;

    /**
     * 
     */
    @Column("`SUBSCRIPTION_START_POINT`")
    private Integer subscriptionStartPoint;

    /**
     * 
     */
    @Column("`SCALE_INITIAL`")
    private Long scaleInitial;

    /**
     * 
     */
    @Column("`ISSUE_FEE`")
    private BigDecimal issueFee;

    /**
     * 
     */
    @Column("`REDEEM_FEE`")
    private BigDecimal redeemFee;

    /**
     * 
     */
    @Column("`MANAGEMENT_FEE`")
    private BigDecimal managementFee;

    /**
     * 
     */
    @Column("`PERFORMANECE_RETURN`")
    private BigDecimal performaneceReturn;

    /**
     * 
     */
    @Column("`RECORD_CD`")
    private String recordCd;

    /**
     * 
     */
    @Column("`UPDATE_TIME`")
    private Date updateTime;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    private Long tmstamp;

    /**
     * 备案日期
     */
    @Column("`RECORD_DATE`")
    private Date recordDate;

    /**
     * 到期日
     */
    @Column("`END_DATE`")
    private Date endDate;

    /**
     * 
     */
    @Column("`NAV_FREQ`")
    private String navFreq;

    /**
     *
     */
    @Column("`MANAGER`")
    private String manager;

    /**
     * 
     */
    @Column("`CLOSING_DURA_DESC`")
    private String closingDuraDesc;

    /**
     * 发行地
     */
    @Column("`ISSUE_LOC`")
    private String issueLoc;

    /**
     * 备案状态
     */
    @Column("`RECORD_STATUS`")
    private String recordStatus;

    /**
     * 申购费率
     */
    @Column("`APPLY_FEE`")
    private BigDecimal applyFee;

}
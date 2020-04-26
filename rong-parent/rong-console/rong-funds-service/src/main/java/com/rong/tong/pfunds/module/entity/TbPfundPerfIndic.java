package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-02-03
 */
@Table("`tong-rong`.`pfund_perf_indic`")
@Data()
@Accessors(chain = true)
public class TbPfundPerfIndic {
    /**
     * 信息编码
     */
    @Column("`ID`")
    private Long id;

    /**
     * 证券内部编码
     */
    @Column("`SECURITY_ID`")
    private Long securityId;

    /**
     * 性能指标计算时间窗口长度
     */
    @Column("`WINDOW`")
    private Integer window;

    /**
     * 数据日期
     */
    @Column("`END_DATE`")
    private Date endDate;

    /**
     * 年化收益
     */
    @Column("`ANNUAL_TOTAL_RETURN`")
    private BigDecimal annualTotalReturn;

    /**
     * 年化风险, 年化波动率
     */
    @Column("`ANNUAL_TOTAL_RISK`")
    private BigDecimal annualTotalRisk;

    /**
     * 年化主动收益
     */
    @Column("`ANNUAL_ACTIVE_RETURN`")
    private BigDecimal annualActiveReturn;

    /**
     * 年化主动风险
     */
    @Column("`ANNUAL_ACTIVE_RISK`")
    private BigDecimal annualActiveRisk;

    /**
     * 非年化主动收益
     */
    @Column("`DEANNUAL_ACTIVE_RETURN`")
    private BigDecimal deannualActiveReturn;

    /**
     * 非年化主动风险
     */
    @Column("`DEANNUAL_ACTIVE_RISK`")
    private BigDecimal deannualActiveRisk;

    /**
     * 最大回撤
     */
    @Column("`MAX_DRAWDOWN`")
    private BigDecimal maxDrawdown;

    /**
     * 下行风险
     */
    @Column("`DOWNSIDE_RISK`")
    private BigDecimal downsideRisk;

    /**
     * 信息比率
     */
    @Column("`IR`")
    private BigDecimal ir;

    /**
     * 夏普比率
     */
    @Column("`SHARPE_RATIO`")
    private BigDecimal sharpeRatio;

    /**
     * 最大回撤修复期数
     */
    @Column("`MAX_DD_REC_DAYS`")
    private Integer maxDdRecDays;

    /**
     * 与股市相关性
     */
    @Column("`CORR_EQUITY`")
    private BigDecimal corrEquity;

    /**
     * 与债市相关性
     */
    @Column("`CORR_BOND`")
    private BigDecimal corrBond;

    /**
     * 与期市相关性
     */
    @Column("`CORR_FUTURE`")
    private BigDecimal corrFuture;

    /**
     * 最大连涨期数
     */
    @Column("`MAX_CONT_RISE_DAYS`")
    private Integer maxContRiseDays;

    /**
     * 最大连跌期数
     */
    @Column("`MAX_CONT_DECL_DAYS`")
    private Integer maxContDeclDays;

    /**
     * 最大连续涨幅
     */
    @Column("`MAX_CONT_RISE_RATE`")
    private BigDecimal maxContRiseRate;

    /**
     * 最大连续跌幅
     */
    @Column("`MAX_CONT_DECL_RATE`")
    private BigDecimal maxContDeclRate;

    /**
     * 最大单日涨幅
     */
    @Column("`MAX_D_RISING_RATE`")
    private BigDecimal maxDRisingRate;

    /**
     * 最大单日跌幅
     */
    @Column("`MAX_D_DECLINE_RATE`")
    private BigDecimal maxDDeclineRate;

    /**
     * 最大连涨区间开始日期
     */
    @Column("`MAX_RISE_BEG_DATE`")
    private Date maxRiseBegDate;

    /**
     * 最大连涨区间结束日期
     */
    @Column("`MAX_RISE_END_DATE`")
    private Date maxRiseEndDate;

    /**
     * 最大连跌区间开始日期
     */
    @Column("`MAX_DECL_BEG_DATE`")
    private Date maxDeclBegDate;

    /**
     * 最大连跌区间结束日期
     */
    @Column("`MAX_DECL_END_DATE`")
    private Date maxDeclEndDate;

    /**
     * 亏损天占比
     */
    @Column("`LOSS_DAYS_RATE`")
    private BigDecimal lossDaysRate;

    /**
     * 盈利天占比
     */
    @Column("`PROFIT_DAYS_RATE`")
    private BigDecimal profitDaysRate;

    /**
     * HURST指数/赫斯特指数
     */
    @Column("`HURST`")
    private BigDecimal hurst;

    /**
     * STUTZER指数
     */
    @Column("`STUTZER`")
    private BigDecimal stutzer;

    /**
     * TREYNOR指数/特雷诺比率
     */
    @Column("`TREYNOR_RATIO`")
    private BigDecimal treynorRatio;

    /**
     * SORTINO指数/索提诺比率
     */
    @Column("`SORTINO_RATIO`")
    private BigDecimal sortinoRatio;

    /**
     * 实现ALPHA
     */
    @Column("`REALIZED_ALPHA`")
    private BigDecimal realizedAlpha;

    /**
     * 实现BETA
     */
    @Column("`REALIZED_BETA`")
    private BigDecimal realizedBeta;

    /**
     * 残差风险
     */
    @Column("`RESIDUAL_RISK`")
    private BigDecimal residualRisk;

    /**
     * M平方
     */
    @Column("`M_SQUARE`")
    private BigDecimal mSquare;

    /**
     * 调整夏普比率
     */
    @Column("`ADJ_SHARP_RATIO`")
    private BigDecimal adjSharpRatio;

    /**
     * Var(95%)/在险价值(95%)
     */
    @Column("`VAR_950`")
    private BigDecimal var950;

    /**
     * 在险价值超额收益（95%）
     */
    @Column("`ER_VAR_950`")
    private BigDecimal erVar950;

    /**
     * CVaR(95%)/条件在险价值(95%)
     */
    @Column("`CVAR_950`")
    private BigDecimal cvar950;

    /**
     * 条件夏普比率（95%）
     */
    @Column("`CSR_950`")
    private BigDecimal csr950;

    /**
     * Var(97.5%)/在险价值(97.5%)
     */
    @Column("`VAR_975`")
    private BigDecimal var975;

    /**
     * 在险价值超额收益（97.5%）
     */
    @Column("`ER_VAR_975`")
    private BigDecimal erVar975;

    /**
     * CVaR(97.5%)/条件在险价值(97.5%)
     */
    @Column("`CVAR_975`")
    private BigDecimal cvar975;

    /**
     * 条件夏普比率（97.5%）
     */
    @Column("`CSR_975`")
    private BigDecimal csr975;

    /**
     * Var(99%)/在险价值(99%)
     */
    @Column("`VAR_990`")
    private BigDecimal var990;

    /**
     * 在险价值超额收益（99%）
     */
    @Column("`ER_VAR_990`")
    private BigDecimal erVar990;

    /**
     * CVaR(99%)/条件在险价值(99%)
     */
    @Column("`CVAR_990`")
    private BigDecimal cvar990;

    /**
     * 条件夏普比率（99%）
     */
    @Column("`CSR_990`")
    private BigDecimal csr990;

    /**
     * 偏度
     */
    @Column("`SKEWNESS`")
    private BigDecimal skewness;

    /**
     * 峰度
     */
    @Column("`KURTOSIS`")
    private BigDecimal kurtosis;

    /**
     * 詹森比率
     */
    @Column("`JENSENS_ALPHA`")
    private BigDecimal jensensAlpha;

    /**
     * 卡玛比率
     */
    @Column("`CALMAR_RATIO`")
    private BigDecimal calmarRatio;

    /**
     * 斯特林比率
     */
    @Column("`STEARLING_RATIO`")
    private BigDecimal stearlingRatio;

    /**
     * Burke比率
     */
    @Column("`BURKE_RATIO`")
    private BigDecimal burkeRatio;

    /**
     * Omega比率
     */
    @Column("`OMEGA_RATIO`")
    private BigDecimal omegaRatio;

    /**
     * Kappa 3比率
     */
    @Column("`KAPPA_3_RATIO`")
    private BigDecimal kappa3Ratio;

    /**
     * Mar比率
     */
    @Column("`MAR_RATIO`")
    private BigDecimal marRatio;

    /**
     * 回归年度回报率
     */
    @Column("`RAR`")
    private BigDecimal rar;

    /**
     * R立方
     */
    @Column("`R_CUBED`")
    private BigDecimal rCubed;

    /**
     * 跟踪误差
     */
    @Column("`TRACKING_ERROR`")
    private BigDecimal trackingError;

    /**
     * R平方
     */
    @Column("`R_SQUARE`")
    private BigDecimal rSquare;

    /**
     * 上行捕获收益
     */
    @Column("`UP_CAPTURE_RETURN`")
    private BigDecimal upCaptureReturn;

    /**
     * 上行捕获率
     */
    @Column("`UP_CAPTURE_RATE`")
    private BigDecimal upCaptureRate;

    /**
     * 下行捕获收益
     */
    @Column("`DOWN_CAPTURE_RETURN`")
    private BigDecimal downCaptureReturn;

    /**
     * 下行捕获率
     */
    @Column("`DOWN_CAPTURE_RATE`")
    private BigDecimal downCaptureRate;

    /**
     * 第二大回撤
     */
    @Column("`SEC_MDD`")
    private BigDecimal secMdd;

    /**
     * 第二大回撤期数
     */
    @Column("`SEC_MDD_PERIODS`")
    private Integer secMddPeriods;

    /**
     * 第二大回撤修复期数
     */
    @Column("`SEC_MDD_RECOVERY`")
    private Integer secMddRecovery;

    /**
     * 第三大回撤
     */
    @Column("`THIRD_MDD`")
    private BigDecimal thirdMdd;

    /**
     * 第三大回撤期数
     */
    @Column("`THIRD_MDD_PERIODS`")
    private Integer thirdMddPeriods;

    /**
     * 第三大回撤修复期数
     */
    @Column("`THIRD_MDD_RECOVERY`")
    private Integer thirdMddRecovery;

    /**
     * 序列相关性/1周滞后
     */
    @Column("`WEEK_AUTO_CORR`")
    private BigDecimal weekAutoCorr;

    /**
     * 牛市贝塔
     */
    @Column("`BULL_BETA`")
    private BigDecimal bullBeta;

    /**
     * 熊市贝塔
     */
    @Column("`BEAR_BETA`")
    private BigDecimal bearBeta;

    /**
     * 择时能力(T-M)
     */
    @Column("`TIMING_T_M`")
    private BigDecimal timingTM;

    /**
     * 选股能力(T-M)
     */
    @Column("`SELECTION_T_M`")
    private BigDecimal selectionTM;

    /**
     * 择时能力(C-L)
     */
    @Column("`TIMING_C_L`")
    private BigDecimal timingCL;

    /**
     * 选股能力(C-L)
     */
    @Column("`SELECTION_C_L`")
    private BigDecimal selectionCL;

    /**
     * 择时能力(H-M)
     */
    @Column("`TIMING_H_M`")
    private BigDecimal timingHM;

    /**
     * 选股能力(H-M)
     */
    @Column("`SELECTION_H_M`")
    private BigDecimal selectionHM;

    /**
     * 阿尔法系数
     */
    @Column("`ALPHA_COEF`")
    private BigDecimal alphaCoef;

    /**
     * 净值数据是否符合标准(符合标准：1，不符合标准：0)
     */
    @Column("`IS_NAV_QUALIFIED`")
    private Boolean isNavQualified;

    /**
     * 最大回撤修复期数（日频）
     */
    @Column("`D_MAX_DD_REC_PERIODS`")
    private Integer dMaxDdRecPeriods;

    /**
     * 最大连续涨幅（日频）
     */
    @Column("`D_MAX_CONT_RISE_RATE`")
    private BigDecimal dMaxContRiseRate;

    /**
     * 最大连涨期数（日频）
     */
    @Column("`D_MAX_CONT_RISE_PERIODS`")
    private Integer dMaxContRisePeriods;

    /**
     * 最大单期涨幅（日频）
     */
    @Column("`D_MAX_PERIOD_RISE_RATE`")
    private BigDecimal dMaxPeriodRiseRate;

    /**
     * 最大连涨区间开始日期（日频）
     */
    @Column("`D_MAX_RISE_BEG_DATE`")
    private Date dMaxRiseBegDate;

    /**
     * 最大连涨区间结束日期（日频）
     */
    @Column("`D_MAX_RISE_END_DATE`")
    private Date dMaxRiseEndDate;

    /**
     * 最大连续跌幅（日频）
     */
    @Column("`D_MAX_CONT_DECL_RATE`")
    private BigDecimal dMaxContDeclRate;

    /**
     *  最大连跌期数（日频）
     */
    @Column("`D_MAX_CONT_DECL_PERIODS`")
    private Integer dMaxContDeclPeriods;

    /**
     *  最大单期跌幅（日频）
     */
    @Column("`D_MAX_PERIOD_DECL_RATE`")
    private BigDecimal dMaxPeriodDeclRate;

    /**
     * 最大连跌区间开始日期（日频）
     */
    @Column("`D_MAX_DECL_BEG_DATE`")
    private Date dMaxDeclBegDate;

    /**
     * 最大连跌区间结束日期（日频）
     */
    @Column("`D_MAX_DECL_END_DATE`")
    private Date dMaxDeclEndDate;

    /**
     * 盈利期数占比（日频）
     */
    @Column("`D_PROF_PERIODS_RATE`")
    private BigDecimal dProfPeriodsRate;

    /**
     * 亏损期数占比（日频）
     */
    @Column("`D_LOSS_PERIODS_RATE`")
    private BigDecimal dLossPeriodsRate;

    /**
     * 最大回撤修复期数（周频）
     */
    @Column("`W_MAX_DD_REC_PERIODS`")
    private Integer wMaxDdRecPeriods;

    /**
     * 最大连续涨幅（周频）
     */
    @Column("`W_MAX_CONT_RISE_RATE`")
    private BigDecimal wMaxContRiseRate;

    /**
     * 最大连涨期数（周频）
     */
    @Column("`W_MAX_CONT_RISE_PERIODS`")
    private Integer wMaxContRisePeriods;

    /**
     * 最大单期涨幅（周频）
     */
    @Column("`W_MAX_PERIOD_RISE_RATE`")
    private BigDecimal wMaxPeriodRiseRate;

    /**
     * 最大连涨区间开始日期（周频）
     */
    @Column("`W_MAX_RISE_BEG_DATE`")
    private Date wMaxRiseBegDate;

    /**
     * 最大连涨区间结束日期（周频）
     */
    @Column("`W_MAX_RISE_END_DATE`")
    private Date wMaxRiseEndDate;

    /**
     * 最大连续跌幅（周频）
     */
    @Column("`W_MAX_CONT_DECL_RATE`")
    private BigDecimal wMaxContDeclRate;

    /**
     * 最大连跌期数（周频）
     */
    @Column("`W_MAX_CONT_DECL_PERIODS`")
    private Integer wMaxContDeclPeriods;

    /**
     * 最大单期跌幅（周频）
     */
    @Column("`W_MAX_PERIOD_DECL_RATE`")
    private BigDecimal wMaxPeriodDeclRate;

    /**
     * 最大连跌区间开始日期（周频）
     */
    @Column("`W_MAX_DECL_BEG_DATE`")
    private Date wMaxDeclBegDate;

    /**
     * 最大连跌区间结束日期（周频）
     */
    @Column("`W_MAX_DECL_END_DATE`")
    private Date wMaxDeclEndDate;

    /**
     * 盈利期数占比（周频）
     */
    @Column("`W_PROF_PERIODS_RATE`")
    private BigDecimal wProfPeriodsRate;

    /**
     * 亏损期数占比（周频）
     */
    @Column("`W_LOSS_PERIODS_RATE`")
    private BigDecimal wLossPeriodsRate;

    /**
     * 最大回撤修复期数（月频）
     */
    @Column("`M_MAX_DD_REC_PERIODS`")
    private Integer mMaxDdRecPeriods;

    /**
     * 最大连续涨幅（月频）
     */
    @Column("`M_MAX_CONT_RISE_RATE`")
    private BigDecimal mMaxContRiseRate;

    /**
     * 最大连涨期数（月频）
     */
    @Column("`M_MAX_CONT_RISE_PERIODS`")
    private Integer mMaxContRisePeriods;

    /**
     * 最大单期涨幅（月频）
     */
    @Column("`M_MAX_PERIOD_RISE_RATE`")
    private BigDecimal mMaxPeriodRiseRate;

    /**
     * 最大连涨区间开始日期（月频）
     */
    @Column("`M_MAX_RISE_BEG_DATE`")
    private Date mMaxRiseBegDate;

    /**
     * 最大连涨区间结束日期（月频）
     */
    @Column("`M_MAX_RISE_END_DATE`")
    private Date mMaxRiseEndDate;

    /**
     * 最大连续跌幅（月频）
     */
    @Column("`M_MAX_CONT_DECL_RATE`")
    private BigDecimal mMaxContDeclRate;

    /**
     * 最大连跌期数（月频）
     */
    @Column("`M_MAX_CONT_DECL_PERIODS`")
    private Integer mMaxContDeclPeriods;

    /**
     * 最大单期跌幅（月频）
     */
    @Column("`M_MAX_PERIOD_DECL_RATE`")
    private BigDecimal mMaxPeriodDeclRate;

    /**
     * 最大连跌区间开始日期（月频）
     */
    @Column("`M_MAX_DECL_BEG_DATE`")
    private Date mMaxDeclBegDate;

    /**
     * 最大连跌区间结束日期（月频）
     */
    @Column("`M_MAX_DECL_END_DATE`")
    private Date mMaxDeclEndDate;

    /**
     * 盈利期数占比（月频）
     */
    @Column("`M_PROF_PERIODS_RATE`")
    private BigDecimal mProfPeriodsRate;

    /**
     * 亏损期数占比（月频）
     */
    @Column("`M_LOSS_PERIODS_RATE`")
    private BigDecimal mLossPeriodsRate;

    /**
     * 最大回撤修复期数（季频）
     */
    @Column("`Q_MAX_DD_REC_PERIODS`")
    private Integer qMaxDdRecPeriods;

    /**
     * 最大连续涨幅（季频）
     */
    @Column("`Q_MAX_CONT_RISE_RATE`")
    private BigDecimal qMaxContRiseRate;

    /**
     * 最大连涨期数（季频）
     */
    @Column("`Q_MAX_CONT_RISE_PERIODS`")
    private Integer qMaxContRisePeriods;

    /**
     * 最大单期涨幅（季频）
     */
    @Column("`Q_MAX_PERIOD_RISE_RATE`")
    private BigDecimal qMaxPeriodRiseRate;

    /**
     * 最大连涨区间开始日期（季频）
     */
    @Column("`Q_MAX_RISE_BEG_DATE`")
    private Date qMaxRiseBegDate;

    /**
     * 最大连涨区间结束日期（季频）
     */
    @Column("`Q_MAX_RISE_END_DATE`")
    private Date qMaxRiseEndDate;

    /**
     * 最大连续跌幅（季频）
     */
    @Column("`Q_MAX_CONT_DECL_RATE`")
    private BigDecimal qMaxContDeclRate;

    /**
     * 最大连跌期数（季频）
     */
    @Column("`Q_MAX_CONT_DECL_PERIODS`")
    private Integer qMaxContDeclPeriods;

    /**
     * 最大单期跌幅（季频）
     */
    @Column("`Q_MAX_PERIOD_DECL_RATE`")
    private BigDecimal qMaxPeriodDeclRate;

    /**
     * 最大连跌区间开始日期（季频）
     */
    @Column("`Q_MAX_DECL_BEG_DATE`")
    private Date qMaxDeclBegDate;

    /**
     * 最大连跌区间结束日期（季频）
     */
    @Column("`Q_MAX_DECL_END_DATE`")
    private Date qMaxDeclEndDate;

    /**
     * 盈利期数占比（季频）
     */
    @Column("`Q_PROF_PERIODS_RATE`")
    private BigDecimal qProfPeriodsRate;

    /**
     * 亏损期数占比（季频）
     */
    @Column("`Q_LOSS_PERIODS_RATE`")
    private BigDecimal qLossPeriodsRate;

    /**
     * 最大回撤修复期数（半年频）
     */
    @Column("`S_MAX_DD_REC_PERIODS`")
    private Integer sMaxDdRecPeriods;

    /**
     * 最大连续涨幅（半年频）
     */
    @Column("`S_MAX_CONT_RISE_RATE`")
    private BigDecimal sMaxContRiseRate;

    /**
     * 最大连涨期数（半年频）
     */
    @Column("`S_MAX_CONT_RISE_PERIODS`")
    private Integer sMaxContRisePeriods;

    /**
     * 最大单期涨幅（半年频）
     */
    @Column("`S_MAX_PERIOD_RISE_RATE`")
    private BigDecimal sMaxPeriodRiseRate;

    /**
     * 最大连涨区间开始日期（半年频）
     */
    @Column("`S_MAX_RISE_BEG_DATE`")
    private Date sMaxRiseBegDate;

    /**
     * 最大连涨区间结束日期（半年频）
     */
    @Column("`S_MAX_RISE_END_DATE`")
    private Date sMaxRiseEndDate;

    /**
     * 最大连续跌幅（半年频）
     */
    @Column("`S_MAX_CONT_DECL_RATE`")
    private BigDecimal sMaxContDeclRate;

    /**
     * 最大连跌期数（半年频）
     */
    @Column("`S_MAX_CONT_DECL_PERIODS`")
    private Integer sMaxContDeclPeriods;

    /**
     * 最大单期跌幅（半年频）
     */
    @Column("`S_MAX_PERIOD_DECL_RATE`")
    private BigDecimal sMaxPeriodDeclRate;

    /**
     * 最大连跌区间开始日期（半年频）
     */
    @Column("`S_MAX_DECL_BEG_DATE`")
    private Date sMaxDeclBegDate;

    /**
     * 最大连跌区间结束日期（半年频）
     */
    @Column("`S_MAX_DECL_END_DATE`")
    private Date sMaxDeclEndDate;

    /**
     * 盈利期数占比（半年频）
     */
    @Column("`S_PROF_PERIODS_RATE`")
    private BigDecimal sProfPeriodsRate;

    /**
     * 亏损期数占比（半年频）
     */
    @Column("`S_LOSS_PERIODS_RATE`")
    private BigDecimal sLossPeriodsRate;

    /**
     * 最大回撤修复期数（年频）
     */
    @Column("`Y_MAX_DD_REC_PERIODS`")
    private Integer yMaxDdRecPeriods;

    /**
     * 最大连续涨幅（年频）
     */
    @Column("`Y_MAX_CONT_RISE_RATE`")
    private BigDecimal yMaxContRiseRate;

    /**
     * 最大连涨期数（年频）
     */
    @Column("`Y_MAX_CONT_RISE_PERIODS`")
    private Integer yMaxContRisePeriods;

    /**
     * 最大单期涨幅（年频）
     */
    @Column("`Y_MAX_PERIOD_RISE_RATE`")
    private BigDecimal yMaxPeriodRiseRate;

    /**
     * 最大连涨区间开始日期（年频）
     */
    @Column("`Y_MAX_RISE_BEG_DATE`")
    private Date yMaxRiseBegDate;

    /**
     * 最大连涨区间结束日期（年频）
     */
    @Column("`Y_MAX_RISE_END_DATE`")
    private Date yMaxRiseEndDate;

    /**
     * 最大连续跌幅（年频）
     */
    @Column("`Y_MAX_CONT_DECL_RATE`")
    private BigDecimal yMaxContDeclRate;

    /**
     * 最大连跌期数（年频）
     */
    @Column("`Y_MAX_CONT_DECL_PERIODS`")
    private Integer yMaxContDeclPeriods;

    /**
     * 最大连跌区间结束日期（年频）
     */
    @Column("`Y_MAX_PERIOD_DECL_RATE`")
    private BigDecimal yMaxPeriodDeclRate;

    /**
     * 盈利期数占比（年频）
     */
    @Column("`Y_PROF_PERIODY_RATE`")
    private BigDecimal yProfPeriodyRate;

    /**
     * 亏损期数占比（年频）
     */
    @Column("`Y_LOSY_PERIODY_RATE`")
    private BigDecimal yLosyPeriodyRate;

    /**
     * 是否有效标识
     */
    @Column("`QA_ACTIVE_FLG`")
    private Boolean qaActiveFlg;

    /**
     * 创建时间
     */
    @Column("`CREATE_TIME`")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    private Date updateTime;

    /**
     * 时间戳
     */
    @Column("`TMSTAMP`")
    private Long tmstamp;

    /**
     * 
     */
    @Column("`ETL_CRC`")
    private Long etlCrc;

    /**
     * 
     */
    @Column("`QA_RULE_CHK_FLG`")
    private Boolean qaRuleChkFlg;

    /**
     * 
     */
    @Column("`QA_MANUAL_FLG`")
    private Boolean qaManualFlg;

    /**
     * 
     */
    @Column("`CREATE_BY`")
    private String createBy;

    /**
     * 
     */
    @Column("`UPDATE_BY`")
    private String updateBy;
}
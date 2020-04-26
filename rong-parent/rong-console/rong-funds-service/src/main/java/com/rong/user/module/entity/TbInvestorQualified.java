package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.PrimaryKey;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 合格投资人资格认证
 * @author      : lether
 * @createDate  : 2020-02-10
 */
@Table("`tb_investor_qualified`")
@Data()
@Accessors(chain = true)
public class TbInvestorQualified extends BaseEntity<TbInvestorQualified> {

    /**
     * 通过状态
     */
    @Column("`state`")
    @ApiModelProperty("通过状态（0：未通过，1：通过认证）")
    private Integer state;
    /**
     * 用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 机构和个人
     */
    @Column("`investor_type`")
    @ApiModelProperty("机构和个人")
    private String investorType;

    /**
     * 目的
     */
    @Column("`purpose`")
    @ApiModelProperty("目的")
    private String purpose;

    /**
     * 预期
     */
    @Column("`expected`")
    @ApiModelProperty("预期")
    private String expected;

    /**
     * 投资年限
     */
    @Column("`investment_period`")
    @ApiModelProperty("投资年限")
    private String investmentPeriod;

    /**
     * 下跌极限
     */
    @Column("`falling_limit`")
    @ApiModelProperty("下跌极限")
    private String fallingLimit;

    /**
     * 投资品种了解
     */
    @Column("`understanding_investments`")
    @ApiModelProperty("投资品种了解")
    private String understandingInvestments;

    /**
     * 投资品种了解
     */
    @Column("`vc_experience`")
    @ApiModelProperty("投资品种了解")
    private String vcExperience;

    /**
     * 金融资产-收入
     */
    @Column("`monetary_assets_income`")
    @ApiModelProperty("金融资产-收入")
    private String monetaryAssetsIncome;

    /**
     * 投资比例
     */
    @Column("`investment_ratio`")
    @ApiModelProperty("投资比例")
    private String investmentRatio;

    /**
     * 收入成长情况
     */
    @Column("`income_growth`")
    @ApiModelProperty("收入成长情况")
    private String incomeGrowth;

    /**
     * 看待投资亏损
     */
    @Column("`treat_investment_losses`")
    @ApiModelProperty("看待投资亏损")
    private String treatInvestmentLosses;

    /**
     * 其他0
     */
    @Column("`other0`")
    @ApiModelProperty("其他0")
    private String other0;

    /**
     * 其他1
     */
    @Column("`other1`")
    @ApiModelProperty("其他1")
    private String other1;

    /**
     * 其他2
     */
    @Column("`other2`")
    @ApiModelProperty("其他2")
    private String other2;

    /**
     * 其他3
     */
    @Column("`other3`")
    @ApiModelProperty("其他3")
    private String other3;

    /**
     * 其他4
     */
    @Column("`other4`")
    @ApiModelProperty("其他4")
    private String other4;

    /**
     * 其他5
     */
    @Column("`other5`")
    @ApiModelProperty("其他5")
    private String other5;

    /**
     * 分数
     */
    @Column("`score`")
    @ApiModelProperty("分数")
    private Integer score;
    /**
     * 认证类型
     */
    @Column("`qualified_result`")
    @ApiModelProperty("认证类型(分数0~7为C1保守型，8~15为C2谨慎型，16~23为C3稳健型，24~31为C4积极型，32~40为C5激进型)")
    private String qualifiedResult;
}
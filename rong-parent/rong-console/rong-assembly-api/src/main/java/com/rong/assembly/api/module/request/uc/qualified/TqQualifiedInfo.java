package com.rong.assembly.api.module.request.uc.qualified;

import com.rong.common.annotation.RegexValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqQualifiedInfo extends TqUserAuthBase {
    @ApiModelProperty(value = "Q1,机构/个人",allowableValues = "A,B")
    private String investorType;

    /**
     * 目的
     */
    @ApiModelProperty(value = "Q2,您选择资产管理业务的目的是",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String purpose;

    /**
     * 预期
     */
    @ApiModelProperty(value = "Q3,您对资产管理产品的收益有何预期",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String expected;

    /**
     * 投资年限
     */
    @ApiModelProperty(value = "Q4,您一般的投资年限是多久",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String investmentPeriod;

    /**
     * 下跌极限
     */
    @ApiModelProperty(value = "Q5,投资有风险，在金融危机中，市场大幅波动，有些投资品的价格短期内可能大幅下跌。您能够承受的投资组合下跌的极限是多少",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String fallingLimit;

    /**
     * 投资品种了解
     */
    @ApiModelProperty(value = "Q6,您对债券、基金、股票、期货四类投资品种，有多少了解",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String understandingInvestments;

    /**
     * 您有多少年投资基金、股票、信托、私募证券或金融衍生产品等风险投资品的经验
     */
    @ApiModelProperty(value = "Q7,您有多少年投资基金、股票、信托、私募证券或金融衍生产品等风险投资品的经验",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String vcExperience;

    /**
     * 金融资产-收入
     */
    @ApiModelProperty(value = "Q8,您的金融资产或者最近三年个人年均收入情况",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String monetaryAssetsIncome;

    /**
     * 投资比例
     */
    @ApiModelProperty(value = "Q9,您投资基金(非货币) 、股票或期货的资金占家庭总流动资产的比例",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String investmentRatio;

    /**
     * 收入成长情况
     */
    @ApiModelProperty(value = "Q10,您未来三年的收入成长情况如何",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String incomeGrowth;

    @ApiModelProperty(value = "Q11,看待投资亏损",allowableValues = "A,B,C,D,E")
    @RegexValidator(regexStr = "[ABCDE]")
    private String treatInvestmentLosses;

}

package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbInvestorQualified;
import lombok.Data;

@Data
public class TsInvestorQualified extends QueryInfo<TbInvestorQualified> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        investorType,
        purpose,
        expected,
        investmentPeriod,
        fallingLimit,
        vcExperience,
        understandingInvestments,
        monetaryAssetsIncome,
        investmentRatio,
        incomeGrowth,
        treatInvestmentLosses,
        state,
        other0,
        other1,
        other2,
        other3,
        other4,
        other5,
        score,
        qualifiedResult
    }
}
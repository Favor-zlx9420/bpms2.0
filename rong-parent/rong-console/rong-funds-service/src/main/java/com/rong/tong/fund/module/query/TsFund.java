package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFund;
import lombok.Data;

@Data
public class TsFund extends QueryInfo<TbFund> {

    public enum Fields {
        id,
        fundId,
        secFullName,
        secShortName,
        managementCompany,
        custodian,
        investmentAdviser,
        managementMode,
        category,
        indexFund,
        etfLof,
        isQdii,
        isFof,
        investTarget,
        perfBenchmark,
        regPlace,
        establishDate,
        updateTime,
        tmstamp,
        perfBenchmarkEn,
        investField;
    }
}
package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundClass;
import lombok.Data;

@Data
public class TsFundClass extends QueryInfo<TbFundClass> {

    public enum Fields {
        id,
        fundId,
        securityId,
        className,
        tickerSymbol,
        exchangeCd,
        classStatus,
        expireDate,
        operationMode,
        etfLof,
        guarRatio,
        guarPeriod,
        isGuarFund,
        updateTime,
        tmstamp,
        isClass,
        clearType,
        establishDate,
        tickerSymbolF,
        tickerSymbolB,
        tickerSymbolSub,
        tickerSymbolPm,
        tickerSymbolM,
        secShortNameEx,
        tickerSymbolEx;
    }
}
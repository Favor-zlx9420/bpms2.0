package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbMdSecurity;
import lombok.Data;

@Data
public class TsMdSecurity extends QueryInfo<TbMdSecurity> {

    public enum Fields {
        securityId,
        tickerSymbol,
        exchangeCd,
        secFullName,
        secShortName,
        cnSpell,
        secFullNameEn,
        secShortNameEn,
        dyid,
        exCountryCd,
        transCurrCd,
        assetClass,
        listStatusCd,
        listDate,
        delistDate,
        partyId,
        dyUseFlg,
        updateTime,
        tmstamp;
    }
}
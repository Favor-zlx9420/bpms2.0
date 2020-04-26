package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundInstInfo;
import lombok.Data;

@Data
public class TsFundInstInfo extends QueryInfo<TbFundInstInfo> {

    public enum Fields {
        id,
        partyId,
        partyFullName,
        partyShortName,
        partyFullNameEn,
        partyShortNameEn,
        regDate,
        legalRep,
        regCountryCd,
        regProvince,
        regCity,
        regAddr,
        regCap,
        regCapCurrCd,
        officeAddr,
        postCode,
        email,
        website,
        tel,
        fax,
        boardSecry,
        genMana,
        legalEntityId,
        creditCode,
        partyNatureCd,
        closeDate,
        instStatus,
        photo,
        partyType,
        partySect,
        updateTime,
        tmstamp,
        opaScope,
        primeOperating,
        profile;
    }
}
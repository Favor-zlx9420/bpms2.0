package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbMdInstitution;
import lombok.Data;

@Data
public class TsMdInstitution extends QueryInfo<TbMdInstitution> {

    public enum Fields {
        partyId,
        partyFullName,
        partyShortName,
        partyFullNameEn,
        partyShortNameEn,
        regDate,
        regCountryCd,
        regProvince,
        regCity,
        regAddr,
        regCap,
        regCapCurrCd,
        officeAddr,
        email,
        website,
        tel,
        fax,
        legalEntityId,
        partyNatureCd,
        isIssBond,
        closeDate,
        instStatus,
        dyUseFlg,
        updateTime,
        tmstamp,
        boardSecry,
        legalRep,
        genMana,
        isIssMf,
        primeOperating,
        profile,
        opaScope;
    }
}
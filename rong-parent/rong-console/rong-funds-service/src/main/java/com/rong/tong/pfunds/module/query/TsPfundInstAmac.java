package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundInstAmac;
import lombok.Data;

@Data
public class TsPfundInstAmac extends QueryInfo<TbPfundInstAmac> {

    public enum Fields {
        id,
        instId,
        partyFullName,
        partyFullNameEn,
        regCd,
        bizCd,
        recordDate,
        regDate,
        regAddr,
        officeAddr,
        regCap,
        actCap,
        instNature,
        actCapRate,
        instType,
        serviceType,
        empNum,
        website,
        isQualifiedAdviser,
        isMember,
        memberRep,
        memberType,
        initiationTime,
        legalOpStatus,
        lawFirm,
        lawyerName,
        legalRep,
        isQualified,
        qualifyWay,
        lastReportDate,
        partyId,
        recordStatus,
        updateTime,
        tmstamp,
        empNumQualified,
        instIntInf,
        note;
    }
}
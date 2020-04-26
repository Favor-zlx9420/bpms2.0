package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import lombok.Data;

@Data
public class TsPfundInstInfo extends QueryInfo<TbPfundInstInfo> {

    public enum Fields {
        id,
        partyId,
        updateTime,
        tmstamp,
        recordDate,
        regCd,
        legalRep,
        isQualified,
        qualifyWay,
        empNum,
        mainFundType,
        privateIndScale,
        privateConScale,
        peScale,
        vcScale,
        otherScale,
        isFundMana,
        paaScale,
        recordStatus,
        profile,
        ideaStrategy,
        keyPerson;
    }
}
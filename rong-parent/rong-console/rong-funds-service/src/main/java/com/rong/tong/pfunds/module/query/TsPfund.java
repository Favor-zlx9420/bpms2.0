package com.rong.tong.pfunds.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.pfunds.module.entity.TbPfund;
import lombok.Data;

@Data
public class TsPfund extends QueryInfo<TbPfund> {

    public enum Fields {
        id,
        securityId,
        establishDate,
        pfStyle,
        status,
        investStrategy,
        investStrategyChild,
        duration,
        openDateDesc,
        investConsultant,
        custodian,
        issuePlatform,
        tradingBroker,
        subscriptionStartPoint,
        scaleInitial,
        issueFee,
        redeemFee,
        managementFee,
        performaneceReturn,
        recordCd,
        updateTime,
        tmstamp,
        recordDate,
        endDate,
        navFreq,
        closingDuraDesc,
        issueLoc,
        recordStatus;
    }
}
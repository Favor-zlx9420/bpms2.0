package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNavGr;
import lombok.Data;

@Data
public class TsRaisedFundCurrentNavGr extends QueryInfo<TbRaisedFundCurrentNavGr> {

    public enum Fields {
        id,
        createDate,
        securityId,
        endDate,
        returnRate,
        returnRateWtd,
        returnRate1w,
        returnRateMtd,
        returnRate1m,
        returnRate3m,
        returnRate6m,
        returnRateYtd,
        returnRate1y,
        returnRate2y,
        returnRate3y,
        returnRate5y,
        returnRateEst,
        personId,
        partyId;
    }
}
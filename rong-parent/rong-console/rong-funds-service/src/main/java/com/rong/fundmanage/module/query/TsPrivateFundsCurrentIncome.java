package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentIncome;
import lombok.Data;

@Data
public class TsPrivateFundsCurrentIncome extends QueryInfo<TbPrivateFundsCurrentIncome> {

    public enum Fields {
        id,
        securityId,
        returnA,
        returnAccum,
        endDate;
    }
}
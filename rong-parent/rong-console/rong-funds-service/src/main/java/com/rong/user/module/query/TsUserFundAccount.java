package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserFundAccount;
import lombok.Data;

@Data
public class TsUserFundAccount extends QueryInfo<TbUserFundAccount> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        securityId,
        userId,
        buyDate,
        thenNav,
        share,
        principal;
    }
}
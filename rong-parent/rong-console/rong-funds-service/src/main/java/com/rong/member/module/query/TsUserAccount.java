package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbUserAccount;
import lombok.Data;

@Data
public class TsUserAccount extends QueryInfo<TbUserAccount> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        freezeAmount,
        availableAmount,
        version,
        type;
    }
}
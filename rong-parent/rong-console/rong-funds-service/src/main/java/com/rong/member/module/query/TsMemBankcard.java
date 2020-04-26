package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemBankcard;
import lombok.Data;

@Data
public class TsMemBankcard extends QueryInfo<TbMemBankcard> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        memberId,
        bankId,
        branch,
        number,
        memo,
        province,
        city,
        area,
        cardType,
        bankName;
    }
}
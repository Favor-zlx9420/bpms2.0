package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemRecInfo;
import lombok.Data;

@Data
public class TsMemRecInfo extends QueryInfo<TbMemRecInfo> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        sort,
        memberId,
        receiver,
        country,
        province,
        city,
        area,
        address,
        postCode,
        phone,
        call,
        defaulted;
    }
}
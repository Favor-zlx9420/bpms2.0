package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemCustomer;
import lombok.Data;

@Data
public class TsMemCustomer extends QueryInfo<TbMemCustomer> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        name,
        phone;
    }
}
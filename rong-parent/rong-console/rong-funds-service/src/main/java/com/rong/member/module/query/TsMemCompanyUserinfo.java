package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemCompanyUserinfo;
import lombok.Data;

@Data
public class TsMemCompanyUserinfo extends QueryInfo<TbMemCompanyUserinfo> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        companyName,
        country,
        province,
        city,
        area,
        address,
        call,
        postCode;
    }
}
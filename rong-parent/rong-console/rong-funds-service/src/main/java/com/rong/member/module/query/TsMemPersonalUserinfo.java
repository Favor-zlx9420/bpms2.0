package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemPersonalUserinfo;
import lombok.Data;

@Data
public class TsMemPersonalUserinfo extends QueryInfo<TbMemPersonalUserinfo> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        birthDate,
        gender,
        realName,
        idType,
        idNo,
        phone,
        country,
        province,
        city,
        area,
        address,
        call,
        postCode;
    }
}
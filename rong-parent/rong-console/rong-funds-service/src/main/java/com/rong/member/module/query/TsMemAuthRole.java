package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemAuthRole;
import lombok.Data;

@Data
public class TsMemAuthRole extends QueryInfo<TbMemAuthRole> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        roleId;
    }
}
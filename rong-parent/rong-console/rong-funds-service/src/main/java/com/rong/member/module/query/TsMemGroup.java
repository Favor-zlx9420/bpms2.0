package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemGroup;
import lombok.Data;

@Data
public class TsMemGroup extends QueryInfo<TbMemGroup> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        name,
        description,
        defaulted,
        percent;
    }
}
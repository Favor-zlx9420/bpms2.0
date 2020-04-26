package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserVipEnd;
import lombok.Data;

@Data
public class TsUserVipEnd extends QueryInfo<TbUserVipEnd> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        level,
        beginDate,
        endDate;
    }
}
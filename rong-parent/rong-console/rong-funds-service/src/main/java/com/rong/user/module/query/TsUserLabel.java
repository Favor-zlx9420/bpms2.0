package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserLabel;
import lombok.Data;

@Data
public class TsUserLabel extends QueryInfo<TbUserLabel> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        labelId,
        visible,
        recommend,
        sort,
        labelReason,
        labelVar0,
        labelVar1,
        labelVar2,
        labelVar3;
    }
}
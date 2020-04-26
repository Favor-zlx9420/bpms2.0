package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbProductLabel;
import lombok.Data;

@Data
public class TsProductLabel extends QueryInfo<TbProductLabel> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        securityId,
        labelId,
        visible,
        recommend,
        sort,
        labelReason,
        labelVar0,
        labelVar1
    }
}
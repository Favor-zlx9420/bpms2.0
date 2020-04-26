package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import lombok.Data;

@Data
public class TsSecurityManage extends QueryInfo<TbSecurityManage> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        securityId,
        visible,
        recommend,
        hotSearch,
        type
    }
}
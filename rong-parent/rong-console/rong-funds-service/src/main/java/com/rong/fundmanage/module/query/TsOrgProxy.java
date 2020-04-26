package com.rong.fundmanage.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.fundmanage.module.entity.TbOrgProxy;
import lombok.Data;

@Data
public class TsOrgProxy extends QueryInfo<TbOrgProxy> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        partyId;
    }
}
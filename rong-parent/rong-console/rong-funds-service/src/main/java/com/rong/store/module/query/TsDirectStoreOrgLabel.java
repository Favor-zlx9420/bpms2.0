package com.rong.store.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import lombok.Data;

@Data
public class TsDirectStoreOrgLabel extends QueryInfo<TbDirectStoreOrgLabel> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        partyId,
        labelId,
        visible,
        recommend,
        sort;
    }
}
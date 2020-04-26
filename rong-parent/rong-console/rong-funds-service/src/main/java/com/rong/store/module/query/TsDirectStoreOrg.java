package com.rong.store.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.store.module.entity.TbDirectStoreOrg;
import lombok.Data;

@Data
public class TsDirectStoreOrg extends QueryInfo<TbDirectStoreOrg> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        partyId,
        userId,
        type,
        visible,
        recommend,
        sort,
        labelVar0,
        labelVar1,
        remark,
    }
}
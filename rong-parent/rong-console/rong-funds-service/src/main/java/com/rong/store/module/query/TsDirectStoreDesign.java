package com.rong.store.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.store.module.entity.TbDirectStoreDesign;
import lombok.Data;

@Data
public class TsDirectStoreDesign extends QueryInfo<TbDirectStoreDesign> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        visible,
        partyId,
        title,
        content,
        sort,
        auditUserId,
        appUserId,
        auditState;
    }
}
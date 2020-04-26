package com.rong.store.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.store.module.entity.TbDirectStoreServiceHistory;
import lombok.Data;

@Data
public class TsDirectStoreServiceHistory extends QueryInfo<TbDirectStoreServiceHistory> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        customerUserId,
        investorUserId,
        partyId,
        content,
        auditResult,
        score,
        comment,
        linkUrl,
        picUrl;
    }
}
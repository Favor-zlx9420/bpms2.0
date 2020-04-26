package com.rong.store.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.store.module.entity.TbDirectStoreProduct;
import lombok.Data;

@Data
public class TsDirectStoreProduct extends QueryInfo<TbDirectStoreProduct> {
    private String secFullName;
    private String partyFullName;
    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        type,
        securityId,
        visible,
        recommend,
        sort;
    }
}
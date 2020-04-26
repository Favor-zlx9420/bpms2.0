package com.rong.store.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.store.module.entity.TbDirectStoreProduct;
import lombok.Data;

import java.util.List;

@Data
public class TqDirectStoreProduct extends BaseRequest<TbDirectStoreProduct, TqDirectStoreProduct> {
    private List<Long> labelIds;
    /**
     * 推荐理由
     */
    private String labelReason;
    /**
     * 自定义标签0
     */
    private String labelVar0;
    /**
     * 自定义标签1
     */
    private String labelVar1;
}
package com.rong.store.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.store.module.entity.TbDirectStoreServiceHistory;
import lombok.Data;

@Data
public class TqDirectStoreServiceHistory extends BaseRequest<TbDirectStoreServiceHistory, TqDirectStoreServiceHistory> {
    private String replyMessage;
}
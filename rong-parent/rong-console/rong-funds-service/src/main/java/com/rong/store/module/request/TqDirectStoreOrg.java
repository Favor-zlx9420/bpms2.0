package com.rong.store.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.store.module.entity.TbDirectStoreOrg;
import lombok.Data;

import java.util.List;

@Data
public class TqDirectStoreOrg extends BaseRequest<TbDirectStoreOrg, TqDirectStoreOrg> {
    private List<Long> labelIds;
}
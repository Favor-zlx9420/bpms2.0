package com.rong.store.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.store.module.entity.TbDirectStoreServiceHistory;
import com.rong.store.module.view.TvDirectStoreServiceHistory;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface DirectStoreServiceHistoryMapper extends CommonBasicMapper<TbDirectStoreServiceHistory, TvDirectStoreServiceHistory>, MultiTableMapper<TbDirectStoreServiceHistory, TvDirectStoreServiceHistory> {
}
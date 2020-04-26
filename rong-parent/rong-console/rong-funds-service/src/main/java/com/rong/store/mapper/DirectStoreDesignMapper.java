package com.rong.store.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.store.module.entity.TbDirectStoreDesign;
import com.rong.store.module.view.TvDirectStoreDesign;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface DirectStoreDesignMapper extends CommonBasicMapper<TbDirectStoreDesign, TvDirectStoreDesign>, MultiTableMapper<TbDirectStoreDesign, TvDirectStoreDesign> {
}
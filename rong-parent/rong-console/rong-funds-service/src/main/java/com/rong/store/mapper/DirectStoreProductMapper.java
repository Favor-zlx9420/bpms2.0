package com.rong.store.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.store.module.entity.TbDirectStoreProduct;
import com.rong.store.module.view.TvDirectStoreProduct;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface DirectStoreProductMapper extends CommonBasicMapper<TbDirectStoreProduct, TvDirectStoreProduct>, MultiTableMapper<TbDirectStoreProduct, TvDirectStoreProduct> {
}
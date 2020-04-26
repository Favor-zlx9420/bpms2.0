package com.rong.store.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.store.module.entity.TbDirectStoreOrg;
import com.rong.store.module.view.TvDirectStoreOrg;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface DirectStoreOrgMapper extends CommonBasicMapper<TbDirectStoreOrg, TvDirectStoreOrg>, MultiTableMapper<TbDirectStoreOrg, TvDirectStoreOrg> {
}
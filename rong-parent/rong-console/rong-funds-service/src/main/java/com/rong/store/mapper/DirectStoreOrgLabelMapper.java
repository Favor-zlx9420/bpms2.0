package com.rong.store.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.store.module.entity.TbDirectStoreOrgLabel;
import com.rong.store.module.view.TvDirectStoreOrgLabel;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface DirectStoreOrgLabelMapper extends CommonBasicMapper<TbDirectStoreOrgLabel, TvDirectStoreOrgLabel>, MultiTableMapper<TbDirectStoreOrgLabel, TvDirectStoreOrgLabel> {
}
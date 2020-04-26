package com.rong.store.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.store.module.entity.TbDirectStoreUser;
import com.rong.store.module.view.TvDirectStoreUser;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface DirectStoreUserMapper extends CommonBasicMapper<TbDirectStoreUser, TvDirectStoreUser>, MultiTableMapper<TbDirectStoreUser, TvDirectStoreUser> {
}
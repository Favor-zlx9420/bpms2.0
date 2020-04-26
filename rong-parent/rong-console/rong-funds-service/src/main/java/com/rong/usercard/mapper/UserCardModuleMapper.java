package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardModule;
import com.rong.usercard.module.view.TvUserCardModule;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardModuleMapper extends CommonBasicMapper<TbUserCardModule, TvUserCardModule>, MultiTableMapper<TbUserCardModule, TvUserCardModule> {
}
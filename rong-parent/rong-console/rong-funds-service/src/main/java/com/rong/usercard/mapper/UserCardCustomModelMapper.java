package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardCustomModel;
import com.rong.usercard.module.view.TvUserCardCustomModel;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardCustomModelMapper extends CommonBasicMapper<TbUserCardCustomModel, TvUserCardCustomModel>, MultiTableMapper<TbUserCardCustomModel, TvUserCardCustomModel> {
}
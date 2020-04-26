package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardLike;
import com.rong.usercard.module.view.TvUserCardLike;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardLikeMapper extends CommonBasicMapper<TbUserCardLike, TvUserCardLike>, MultiTableMapper<TbUserCardLike, TvUserCardLike> {
}
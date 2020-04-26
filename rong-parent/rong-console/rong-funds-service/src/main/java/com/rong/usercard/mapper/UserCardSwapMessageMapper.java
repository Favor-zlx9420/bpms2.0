package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardSwapMessage;
import com.rong.usercard.module.view.TvUserCardSwapMessage;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardSwapMessageMapper extends CommonBasicMapper<TbUserCardSwapMessage, TvUserCardSwapMessage>, MultiTableMapper<TbUserCardSwapMessage, TvUserCardSwapMessage> {
}
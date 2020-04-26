package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbMessageHistory;
import com.rong.user.module.view.TvMessageHistory;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MessageHistoryMapper extends CommonBasicMapper<TbMessageHistory, TvMessageHistory>, MultiTableMapper<TbMessageHistory, TvMessageHistory> {
}
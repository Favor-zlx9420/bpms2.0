package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardBrowseHistory;
import com.rong.usercard.module.view.TvUserCardBrowseHistory;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardBrowseHistoryMapper extends CommonBasicMapper<TbUserCardBrowseHistory, TvUserCardBrowseHistory>, MultiTableMapper<TbUserCardBrowseHistory, TvUserCardBrowseHistory> {
}
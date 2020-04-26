package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardTalk;
import com.rong.usercard.module.view.TvUserCardTalk;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardTalkMapper extends CommonBasicMapper<TbUserCardTalk, TvUserCardTalk>, MultiTableMapper<TbUserCardTalk, TvUserCardTalk> {
}
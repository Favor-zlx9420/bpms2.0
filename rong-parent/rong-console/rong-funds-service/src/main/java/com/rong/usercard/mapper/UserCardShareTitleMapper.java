package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardShareTitle;
import com.rong.usercard.module.view.TvUserCardShareTitle;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardShareTitleMapper extends CommonBasicMapper<TbUserCardShareTitle, TvUserCardShareTitle>, MultiTableMapper<TbUserCardShareTitle, TvUserCardShareTitle> {
}
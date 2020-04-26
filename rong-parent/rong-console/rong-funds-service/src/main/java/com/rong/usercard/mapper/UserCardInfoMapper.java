package com.rong.usercard.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.view.TvUserCardInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserCardInfoMapper extends CommonBasicMapper<TbUserCardInfo, TvUserCardInfo>, MultiTableMapper<TbUserCardInfo, TvUserCardInfo> {
}
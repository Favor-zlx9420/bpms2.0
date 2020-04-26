package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserLabel;
import com.rong.user.module.view.TvUserLabel;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserLabelMapper extends CommonBasicMapper<TbUserLabel, TvUserLabel>, MultiTableMapper<TbUserLabel, TvUserLabel> {
}
package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.module.view.TvLabel;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface LabelMapper extends CommonBasicMapper<TbLabel, TvLabel>, MultiTableMapper<TbLabel, TvLabel> {
}
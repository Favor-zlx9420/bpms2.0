package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbProductLabel;
import com.rong.fundmanage.module.view.TvProductLabel;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface ProductLabelMapper extends CommonBasicMapper<TbProductLabel, TvProductLabel>, MultiTableMapper<TbProductLabel, TvProductLabel> {
}
package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundType;
import com.rong.tong.pfunds.module.view.TvPfundType;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundTypeMapper extends CommonBasicMapper<TbPfundType, TvPfundType>, MultiTableMapper<TbPfundType, TvPfundType> {
}
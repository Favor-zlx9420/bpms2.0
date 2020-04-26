package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundManaYear;
import com.rong.tong.pfunds.module.view.TvPfundManaYear;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundManaYearMapper extends CommonBasicMapper<TbPfundManaYear, TvPfundManaYear>, MultiTableMapper<TbPfundManaYear, TvPfundManaYear> {
}
package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbNewsIndustryNew;
import com.rong.tong.pfunds.module.view.TvNewsIndustryNew;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface NewsIndustryNewMapper extends CommonBasicMapper<TbNewsIndustryNew, TvNewsIndustryNew>, MultiTableMapper<TbNewsIndustryNew, TvNewsIndustryNew> {
}
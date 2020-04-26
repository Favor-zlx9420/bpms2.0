package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundPerformance;
import com.rong.tong.fund.module.view.TvFundPerformance;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundPerformanceMapper extends CommonBasicMapper<TbFundPerformance, TvFundPerformance>, MultiTableMapper<TbFundPerformance, TvFundPerformance> {
}
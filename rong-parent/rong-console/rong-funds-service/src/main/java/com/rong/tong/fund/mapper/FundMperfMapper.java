package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundMperf;
import com.rong.tong.fund.module.view.TvFundMperf;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundMperfMapper extends CommonBasicMapper<TbFundMperf, TvFundMperf>, MultiTableMapper<TbFundMperf, TvFundMperf> {
}
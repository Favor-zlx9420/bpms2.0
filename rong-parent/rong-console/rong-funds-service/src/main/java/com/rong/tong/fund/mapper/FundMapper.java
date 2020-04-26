package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFund;
import com.rong.tong.fund.module.view.TvFund;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundMapper extends CommonBasicMapper<TbFund, TvFund>, MultiTableMapper<TbFund, TvFund> {
}
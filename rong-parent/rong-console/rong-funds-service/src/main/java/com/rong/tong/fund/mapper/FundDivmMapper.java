package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundDivm;
import com.rong.tong.fund.module.view.TvFundDivm;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundDivmMapper extends CommonBasicMapper<TbFundDivm, TvFundDivm>, MultiTableMapper<TbFundDivm, TvFundDivm> {
}
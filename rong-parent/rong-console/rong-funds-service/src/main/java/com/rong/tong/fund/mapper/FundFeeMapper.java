package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundFee;
import com.rong.tong.fund.module.view.TvFundFee;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundFeeMapper extends CommonBasicMapper<TbFundFee, TvFundFee>, MultiTableMapper<TbFundFee, TvFundFee> {
}
package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundManagerNew;
import com.rong.tong.fund.module.view.TvFundManagerNew;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundManagerNewMapper extends CommonBasicMapper<TbFundManagerNew, TvFundManagerNew>, MultiTableMapper<TbFundManagerNew, TvFundManagerNew> {
}
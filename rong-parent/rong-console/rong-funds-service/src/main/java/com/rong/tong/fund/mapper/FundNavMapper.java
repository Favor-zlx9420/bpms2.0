package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundNav;
import com.rong.tong.fund.module.view.TvFundNav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundNavMapper extends CommonBasicMapper<TbFundNav, TvFundNav>, MultiTableMapper<TbFundNav, TvFundNav> {
}
package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundManagerInfo;
import com.rong.tong.fund.module.view.TvFundManagerInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundManagerInfoMapper extends CommonBasicMapper<TbFundManagerInfo, TvFundManagerInfo>, MultiTableMapper<TbFundManagerInfo, TvFundManagerInfo> {
}
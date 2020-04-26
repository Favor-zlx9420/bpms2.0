package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundLeverageInfo;
import com.rong.tong.fund.module.view.TvFundLeverageInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundLeverageInfoMapper extends CommonBasicMapper<TbFundLeverageInfo, TvFundLeverageInfo>, MultiTableMapper<TbFundLeverageInfo, TvFundLeverageInfo> {
}
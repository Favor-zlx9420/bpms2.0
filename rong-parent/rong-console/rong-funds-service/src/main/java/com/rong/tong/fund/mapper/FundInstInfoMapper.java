package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundInstInfo;
import com.rong.tong.fund.module.view.TvFundInstInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface FundInstInfoMapper extends CommonBasicMapper<TbFundInstInfo, TvFundInstInfo>, MultiTableMapper<TbFundInstInfo, TvFundInstInfo> {
}
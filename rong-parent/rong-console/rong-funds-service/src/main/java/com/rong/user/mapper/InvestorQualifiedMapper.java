package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbInvestorQualified;
import com.rong.user.module.view.TvInvestorQualified;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface InvestorQualifiedMapper extends CommonBasicMapper<TbInvestorQualified, TvInvestorQualified>, MultiTableMapper<TbInvestorQualified, TvInvestorQualified> {
}
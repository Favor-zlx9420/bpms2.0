package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundPerformanceMapper;
import com.rong.tong.fund.module.entity.TbFundPerformance;
import com.rong.tong.fund.module.request.TqFundPerformance;
import com.rong.tong.fund.module.view.TvFundPerformance;
import com.rong.tong.fund.service.FundPerformanceService;
import org.springframework.stereotype.Service;

@Service
public class FundPerformanceServiceImpl extends FundsBasicServiceImpl<TbFundPerformance, TqFundPerformance, TvFundPerformance, FundPerformanceMapper> implements FundPerformanceService {
}
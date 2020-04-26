package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundMperfMapper;
import com.rong.tong.fund.module.entity.TbFundMperf;
import com.rong.tong.fund.module.request.TqFundMperf;
import com.rong.tong.fund.module.view.TvFundMperf;
import com.rong.tong.fund.service.FundMperfService;
import org.springframework.stereotype.Service;

@Service
public class FundMperfServiceImpl extends FundsBasicServiceImpl<TbFundMperf, TqFundMperf, TvFundMperf, FundMperfMapper> implements FundMperfService {
}
package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundFeeMapper;
import com.rong.tong.fund.module.entity.TbFundFee;
import com.rong.tong.fund.module.request.TqFundFee;
import com.rong.tong.fund.module.view.TvFundFee;
import com.rong.tong.fund.service.FundFeeService;
import org.springframework.stereotype.Service;

@Service
public class FundFeeServiceImpl extends FundsBasicServiceImpl<TbFundFee, TqFundFee, TvFundFee, FundFeeMapper> implements FundFeeService {
}
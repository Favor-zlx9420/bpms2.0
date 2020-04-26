package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundMapper;
import com.rong.tong.fund.module.entity.TbFund;
import com.rong.tong.fund.module.request.TqFund;
import com.rong.tong.fund.module.view.TvFund;
import com.rong.tong.fund.service.FundService;
import org.springframework.stereotype.Service;

@Service
public class FundServiceImpl extends FundsBasicServiceImpl<TbFund, TqFund, TvFund, FundMapper> implements FundService {
}
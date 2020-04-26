package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundDivmMapper;
import com.rong.tong.fund.module.entity.TbFundDivm;
import com.rong.tong.fund.module.request.TqFundDivm;
import com.rong.tong.fund.module.view.TvFundDivm;
import com.rong.tong.fund.service.FundDivmService;
import org.springframework.stereotype.Service;

@Service
public class FundDivmServiceImpl extends FundsBasicServiceImpl<TbFundDivm, TqFundDivm, TvFundDivm, FundDivmMapper> implements FundDivmService {
}
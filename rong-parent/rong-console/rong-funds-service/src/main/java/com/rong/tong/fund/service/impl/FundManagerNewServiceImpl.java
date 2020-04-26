package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundManagerNewMapper;
import com.rong.tong.fund.module.entity.TbFundManagerNew;
import com.rong.tong.fund.module.request.TqFundManagerNew;
import com.rong.tong.fund.module.view.TvFundManagerNew;
import com.rong.tong.fund.service.FundManagerNewService;
import org.springframework.stereotype.Service;

@Service
public class FundManagerNewServiceImpl extends FundsBasicServiceImpl<TbFundManagerNew, TqFundManagerNew, TvFundManagerNew, FundManagerNewMapper> implements FundManagerNewService {
}
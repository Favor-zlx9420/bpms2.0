package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundManagerInfoMapper;
import com.rong.tong.fund.module.entity.TbFundManagerInfo;
import com.rong.tong.fund.module.request.TqFundManagerInfo;
import com.rong.tong.fund.module.view.TvFundManagerInfo;
import com.rong.tong.fund.service.FundManagerInfoService;
import org.springframework.stereotype.Service;

@Service
public class FundManagerInfoServiceImpl extends FundsBasicServiceImpl<TbFundManagerInfo, TqFundManagerInfo, TvFundManagerInfo, FundManagerInfoMapper> implements FundManagerInfoService {
}
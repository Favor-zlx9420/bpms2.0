package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundInstInfoMapper;
import com.rong.tong.fund.module.entity.TbFundInstInfo;
import com.rong.tong.fund.module.request.TqFundInstInfo;
import com.rong.tong.fund.module.view.TvFundInstInfo;
import com.rong.tong.fund.service.FundInstInfoService;
import org.springframework.stereotype.Service;

@Service
public class FundInstInfoServiceImpl extends FundsBasicServiceImpl<TbFundInstInfo, TqFundInstInfo, TvFundInstInfo, FundInstInfoMapper> implements FundInstInfoService {
}
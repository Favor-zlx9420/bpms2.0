package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundLeverageInfoMapper;
import com.rong.tong.fund.module.entity.TbFundLeverageInfo;
import com.rong.tong.fund.module.request.TqFundLeverageInfo;
import com.rong.tong.fund.module.view.TvFundLeverageInfo;
import com.rong.tong.fund.service.FundLeverageInfoService;
import org.springframework.stereotype.Service;

@Service
public class FundLeverageInfoServiceImpl extends FundsBasicServiceImpl<TbFundLeverageInfo, TqFundLeverageInfo, TvFundLeverageInfo, FundLeverageInfoMapper> implements FundLeverageInfoService {
}
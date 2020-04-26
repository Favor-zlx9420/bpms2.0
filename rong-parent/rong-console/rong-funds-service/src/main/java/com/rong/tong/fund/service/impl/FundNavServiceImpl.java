package com.rong.tong.fund.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.fund.mapper.FundNavMapper;
import com.rong.tong.fund.module.entity.TbFundNav;
import com.rong.tong.fund.module.request.TqFundNav;
import com.rong.tong.fund.module.view.TvFundNav;
import com.rong.tong.fund.service.FundNavService;
import org.springframework.stereotype.Service;

@Service
public class FundNavServiceImpl extends FundsBasicServiceImpl<TbFundNav, TqFundNav, TvFundNav, FundNavMapper> implements FundNavService {
}
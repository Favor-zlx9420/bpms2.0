package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.InvestorQualifiedMapper;
import com.rong.user.module.entity.TbInvestorQualified;
import com.rong.user.module.request.TqInvestorQualified;
import com.rong.user.module.view.TvInvestorQualified;
import com.rong.user.service.InvestorQualifiedService;
import org.springframework.stereotype.Service;

@Service
public class InvestorQualifiedServiceImpl extends BasicServiceImpl<TbInvestorQualified, TqInvestorQualified, TvInvestorQualified, InvestorQualifiedMapper> implements InvestorQualifiedService {
}
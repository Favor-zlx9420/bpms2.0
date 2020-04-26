package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundResumeMapper;
import com.rong.tong.pfunds.module.entity.TbPfundResume;
import com.rong.tong.pfunds.module.request.TqPfundResume;
import com.rong.tong.pfunds.module.view.TvPfundResume;
import com.rong.tong.pfunds.service.PfundResumeService;
import org.springframework.stereotype.Service;

@Service
public class PfundResumeServiceImpl extends FundsBasicServiceImpl<TbPfundResume, TqPfundResume, TvPfundResume, PfundResumeMapper> implements PfundResumeService {
}
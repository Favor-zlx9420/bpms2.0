package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundManaYearMapper;
import com.rong.tong.pfunds.module.entity.TbPfundManaYear;
import com.rong.tong.pfunds.module.request.TqPfundManaYear;
import com.rong.tong.pfunds.module.view.TvPfundManaYear;
import com.rong.tong.pfunds.service.PfundManaYearService;
import org.springframework.stereotype.Service;

@Service
public class PfundManaYearServiceImpl extends FundsBasicServiceImpl<TbPfundManaYear, TqPfundManaYear, TvPfundManaYear, PfundManaYearMapper> implements PfundManaYearService {
}
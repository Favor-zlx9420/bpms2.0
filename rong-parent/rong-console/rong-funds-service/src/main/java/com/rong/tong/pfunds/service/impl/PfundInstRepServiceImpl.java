package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundInstRepMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstRep;
import com.rong.tong.pfunds.module.request.TqPfundInstRep;
import com.rong.tong.pfunds.module.view.TvPfundInstRep;
import com.rong.tong.pfunds.service.PfundInstRepService;
import org.springframework.stereotype.Service;

@Service
public class PfundInstRepServiceImpl extends FundsBasicServiceImpl<TbPfundInstRep, TqPfundInstRep, TvPfundInstRep, PfundInstRepMapper> implements PfundInstRepService {
}
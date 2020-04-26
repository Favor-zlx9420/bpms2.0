package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundManagerRepMapper;
import com.rong.tong.pfunds.module.entity.TbPfundManagerRep;
import com.rong.tong.pfunds.module.request.TqPfundManagerRep;
import com.rong.tong.pfunds.module.view.TvPfundManagerRep;
import com.rong.tong.pfunds.service.PfundManagerRepService;
import org.springframework.stereotype.Service;

@Service
public class PfundManagerRepServiceImpl extends FundsBasicServiceImpl<TbPfundManagerRep, TqPfundManagerRep, TvPfundManagerRep, PfundManagerRepMapper> implements PfundManagerRepService {
}
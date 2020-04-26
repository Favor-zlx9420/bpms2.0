package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundInstManagerMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstManager;
import com.rong.tong.pfunds.module.request.TqPfundInstManager;
import com.rong.tong.pfunds.module.view.TvPfundInstManager;
import com.rong.tong.pfunds.service.PfundInstManagerService;
import org.springframework.stereotype.Service;

@Service
public class PfundInstManagerServiceImpl extends FundsBasicServiceImpl<TbPfundInstManager, TqPfundInstManager, TvPfundInstManager, PfundInstManagerMapper> implements PfundInstManagerService {
}
package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundInstAmacMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstAmac;
import com.rong.tong.pfunds.module.request.TqPfundInstAmac;
import com.rong.tong.pfunds.module.view.TvPfundInstAmac;
import com.rong.tong.pfunds.service.PfundInstAmacService;
import org.springframework.stereotype.Service;

@Service
public class PfundInstAmacServiceImpl extends FundsBasicServiceImpl<TbPfundInstAmac, TqPfundInstAmac, TvPfundInstAmac, PfundInstAmacMapper> implements PfundInstAmacService {
}
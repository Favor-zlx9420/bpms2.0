package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundInstScaleAmacMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstScaleAmac;
import com.rong.tong.pfunds.module.request.TqPfundInstScaleAmac;
import com.rong.tong.pfunds.module.view.TvPfundInstScaleAmac;
import com.rong.tong.pfunds.service.PfundInstScaleAmacService;
import org.springframework.stereotype.Service;

@Service
public class PfundInstScaleAmacServiceImpl extends FundsBasicServiceImpl<TbPfundInstScaleAmac, TqPfundInstScaleAmac, TvPfundInstScaleAmac, PfundInstScaleAmacMapper> implements PfundInstScaleAmacService {
}
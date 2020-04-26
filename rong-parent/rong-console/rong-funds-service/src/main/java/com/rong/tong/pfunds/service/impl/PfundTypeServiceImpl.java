package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundTypeMapper;
import com.rong.tong.pfunds.module.entity.TbPfundType;
import com.rong.tong.pfunds.module.request.TqPfundType;
import com.rong.tong.pfunds.module.view.TvPfundType;
import com.rong.tong.pfunds.service.PfundTypeService;
import org.springframework.stereotype.Service;

@Service
public class PfundTypeServiceImpl extends FundsBasicServiceImpl<TbPfundType, TqPfundType, TvPfundType, PfundTypeMapper> implements PfundTypeService {
}
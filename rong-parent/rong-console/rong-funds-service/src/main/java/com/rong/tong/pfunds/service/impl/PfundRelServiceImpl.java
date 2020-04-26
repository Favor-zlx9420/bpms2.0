package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundRelMapper;
import com.rong.tong.pfunds.module.entity.TbPfundRel;
import com.rong.tong.pfunds.module.request.TqPfundRel;
import com.rong.tong.pfunds.module.view.TvPfundRel;
import com.rong.tong.pfunds.service.PfundRelService;
import org.springframework.stereotype.Service;

@Service
public class PfundRelServiceImpl extends FundsBasicServiceImpl<TbPfundRel, TqPfundRel, TvPfundRel, PfundRelMapper> implements PfundRelService {
}
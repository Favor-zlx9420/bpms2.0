package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundPerfIndicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundPerfIndic;
import com.rong.tong.pfunds.module.request.TqPfundPerfIndic;
import com.rong.tong.pfunds.module.view.TvPfundPerfIndic;
import com.rong.tong.pfunds.service.PfundPerfIndicService;
import org.springframework.stereotype.Service;

@Service
public class PfundPerfIndicServiceImpl extends FundsBasicServiceImpl<TbPfundPerfIndic, TqPfundPerfIndic, TvPfundPerfIndic, PfundPerfIndicMapper> implements PfundPerfIndicService {
}
package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.tong.pfunds.mapper.NewsIndustryNewMapper;
import com.rong.tong.pfunds.module.entity.TbNewsIndustryNew;
import com.rong.tong.pfunds.module.request.TqNewsIndustryNew;
import com.rong.tong.pfunds.module.view.TvNewsIndustryNew;
import com.rong.tong.pfunds.service.NewsIndustryNewService;
import org.springframework.stereotype.Service;

@Service
public class NewsIndustryNewServiceImpl extends BasicServiceImpl<TbNewsIndustryNew, TqNewsIndustryNew, TvNewsIndustryNew, NewsIndustryNewMapper> implements NewsIndustryNewService {
}
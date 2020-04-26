package com.rong.sys.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbAdvertise;
import com.rong.sys.module.request.TqAdvertise;
import com.rong.sys.module.view.TvAdvertise;
import com.rong.sys.mapper.AdvertiseMapper;
import com.rong.sys.service.AdvertiseService;
import org.springframework.stereotype.Component;

@Component
public class AdvertiseServiceImpl extends BasicServiceImpl<TbAdvertise, TqAdvertise, TvAdvertise, AdvertiseMapper> implements AdvertiseService {
}
package com.rong.sys.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbPayWay;
import com.rong.sys.module.request.TqPayWay;
import com.rong.sys.module.view.TvPayWay;
import com.rong.sys.mapper.PayWayMapper;
import com.rong.sys.service.PayWayService;
import org.springframework.stereotype.Component;

@Component
public class PayWayServiceImpl extends BasicServiceImpl<TbPayWay, TqPayWay, TvPayWay, PayWayMapper> implements PayWayService {
}
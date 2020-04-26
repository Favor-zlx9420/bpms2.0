package com.rong.sys.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbDeliveryWay;
import com.rong.sys.module.request.TqDeliveryWay;
import com.rong.sys.module.view.TvDeliveryWay;
import com.rong.sys.mapper.DeliveryWayMapper;
import com.rong.sys.service.DeliveryWayService;
import org.springframework.stereotype.Component;

@Component
public class DeliveryWayServiceImpl extends BasicServiceImpl<TbDeliveryWay, TqDeliveryWay, TvDeliveryWay, DeliveryWayMapper> implements DeliveryWayService {
}
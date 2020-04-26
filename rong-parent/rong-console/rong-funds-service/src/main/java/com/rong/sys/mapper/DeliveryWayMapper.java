package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbDeliveryWay;
import com.rong.sys.module.view.TvDeliveryWay;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface DeliveryWayMapper extends CommonBasicMapper<TbDeliveryWay, TvDeliveryWay>, MultiTableMapper<TbDeliveryWay, TvDeliveryWay> {
}
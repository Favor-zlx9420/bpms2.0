package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbPayWay;
import com.rong.sys.module.view.TvPayWay;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PayWayMapper extends CommonBasicMapper<TbPayWay, TvPayWay>, MultiTableMapper<TbPayWay, TvPayWay> {
}
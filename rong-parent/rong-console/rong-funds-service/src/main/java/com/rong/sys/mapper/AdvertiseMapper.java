package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbAdvertise;
import com.rong.sys.module.view.TvAdvertise;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AdvertiseMapper extends CommonBasicMapper<TbAdvertise, TvAdvertise>, MultiTableMapper<TbAdvertise, TvAdvertise> {
}
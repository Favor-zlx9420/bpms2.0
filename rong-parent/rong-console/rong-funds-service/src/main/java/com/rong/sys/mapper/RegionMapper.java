package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbRegion;
import com.rong.sys.module.view.TvRegion;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface RegionMapper extends CommonBasicMapper<TbRegion, TvRegion>, MultiTableMapper<TbRegion, TvRegion> {
}
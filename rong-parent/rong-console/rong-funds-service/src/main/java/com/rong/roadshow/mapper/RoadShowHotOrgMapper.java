package com.rong.roadshow.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.roadshow.module.entity.TbRoadShowHotOrg;
import com.rong.roadshow.module.view.TvRoadShowHotOrg;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface RoadShowHotOrgMapper extends CommonBasicMapper<TbRoadShowHotOrg, TvRoadShowHotOrg>, MultiTableMapper<TbRoadShowHotOrg, TvRoadShowHotOrg> {
}
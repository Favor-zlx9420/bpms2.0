package com.rong.roadshow.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.roadshow.module.view.TvRoadShowInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface RoadShowInfoMapper extends CommonBasicMapper<TbRoadShowInfo, TvRoadShowInfo>, MultiTableMapper<TbRoadShowInfo, TvRoadShowInfo> {
    int countOrgs();
}
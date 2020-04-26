package com.rong.roadshow.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.roadshow.module.entity.TbRoadShowHotUser;
import com.rong.roadshow.module.view.TvRoadShowHotUser;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface RoadShowHotUserMapper extends CommonBasicMapper<TbRoadShowHotUser, TvRoadShowHotUser>, MultiTableMapper<TbRoadShowHotUser, TvRoadShowHotUser> {
}
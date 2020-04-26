package com.rong.roadshow.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.roadshow.module.entity.TbRoadShowCategory;
import com.rong.roadshow.module.view.TvRoadShowCategory;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface RoadShowCategoryMapper extends CommonBasicMapper<TbRoadShowCategory, TvRoadShowCategory>, MultiTableMapper<TbRoadShowCategory, TvRoadShowCategory> {
}
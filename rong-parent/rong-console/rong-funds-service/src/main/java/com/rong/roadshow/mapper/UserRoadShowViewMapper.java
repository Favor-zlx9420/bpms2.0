package com.rong.roadshow.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.roadshow.module.entity.TbUserRoadShowView;
import com.rong.roadshow.module.view.TvUserRoadShowView;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserRoadShowViewMapper extends CommonBasicMapper<TbUserRoadShowView, TvUserRoadShowView>, MultiTableMapper<TbUserRoadShowView, TvUserRoadShowView> {
}
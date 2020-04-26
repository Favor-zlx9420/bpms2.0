package com.rong.roadshow.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.roadshow.module.entity.TbUserRoadShowFav;
import com.rong.roadshow.module.view.TvUserRoadShowFav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserRoadShowFavMapper extends CommonBasicMapper<TbUserRoadShowFav, TvUserRoadShowFav>, MultiTableMapper<TbUserRoadShowFav, TvUserRoadShowFav> {
}
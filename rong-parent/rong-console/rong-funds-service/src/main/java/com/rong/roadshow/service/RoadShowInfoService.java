package com.rong.roadshow.service;

import com.rong.common.service.BasicService;
import com.rong.roadshow.module.entity.TbRoadShowInfo;
import com.rong.roadshow.module.request.TqRoadShowInfo;
import com.rong.roadshow.module.view.TvRoadShowInfo;

public interface RoadShowInfoService extends BasicService<TbRoadShowInfo, TqRoadShowInfo, TvRoadShowInfo> {
    int countOrgs();
}
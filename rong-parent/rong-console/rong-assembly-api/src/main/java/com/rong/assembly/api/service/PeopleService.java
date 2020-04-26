package com.rong.assembly.api.service;

import com.rong.assembly.api.module.request.TqPeopleInfo;
import com.rong.assembly.api.module.request.uc.fav.TqFavPeopleList;
import com.rong.assembly.api.module.response.people.TrFavManager;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.common.module.TvPageList;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;

public interface PeopleService {
    MultiTableQueryWrapper getCommonOfManager(Class<?> tbclass, String peopleIdStr, Long userId);
    TrManager info(TqPeopleInfo req);
    TvPageList<TrFavManager> selectFavManager(TqFavPeopleList req);
}

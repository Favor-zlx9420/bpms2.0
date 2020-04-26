package com.rong.user.service;

import com.rong.common.service.BasicService;
import com.rong.user.module.entity.TbUserPeopleFav;
import com.rong.user.module.query.TsUserPeopleFav;
import com.rong.user.module.request.TqUserPeopleFav;
import com.rong.user.module.view.TvUserPeopleFav;

import java.util.List;

public interface UserPeopleFavService extends BasicService<TbUserPeopleFav, TqUserPeopleFav, TvUserPeopleFav> {
    List<TvUserPeopleFav> selectPriManagerFav(TsUserPeopleFav query);
}
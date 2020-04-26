package com.rong.user.service;

import com.rong.common.service.BasicService;
import com.rong.user.module.entity.TbUserOrgFav;
import com.rong.user.module.query.TsUserOrgFav;
import com.rong.user.module.request.TqUserOrgFav;
import com.rong.user.module.view.TvUserOrgFav;

import java.util.List;

public interface UserOrgFavService extends BasicService<TbUserOrgFav, TqUserOrgFav, TvUserOrgFav> {
    List<TvUserOrgFav> selectPriOrgFav(TsUserOrgFav query);
}
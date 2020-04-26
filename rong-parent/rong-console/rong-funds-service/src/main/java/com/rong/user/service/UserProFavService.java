package com.rong.user.service;

import com.rong.common.service.BasicService;
import com.rong.user.module.entity.TbUserProFav;
import com.rong.user.module.query.TsUserProFav;
import com.rong.user.module.request.TqUserProFav;
import com.rong.user.module.view.TvUserProFav;

import java.util.List;

public interface UserProFavService extends BasicService<TbUserProFav, TqUserProFav, TvUserProFav> {
    List<TvUserProFav> selectPriFundsFav(TsUserProFav query);
}
package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserProFavMapper;
import com.rong.user.module.entity.TbUserProFav;
import com.rong.user.module.query.TsUserProFav;
import com.rong.user.module.request.TqUserProFav;
import com.rong.user.module.view.TvUserProFav;
import com.rong.user.service.UserProFavService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProFavServiceImpl extends BasicServiceImpl<TbUserProFav, TqUserProFav, TvUserProFav, UserProFavMapper> implements UserProFavService {
    @Override
    public List<TvUserProFav> selectPriFundsFav(TsUserProFav query) {
        return mapper.selectPriFundsFav(query);
    }
}
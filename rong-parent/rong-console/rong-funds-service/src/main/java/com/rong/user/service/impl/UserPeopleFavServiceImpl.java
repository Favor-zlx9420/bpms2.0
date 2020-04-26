package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserPeopleFavMapper;
import com.rong.user.module.entity.TbUserPeopleFav;
import com.rong.user.module.query.TsUserPeopleFav;
import com.rong.user.module.request.TqUserPeopleFav;
import com.rong.user.module.view.TvUserPeopleFav;
import com.rong.user.service.UserPeopleFavService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPeopleFavServiceImpl extends BasicServiceImpl<TbUserPeopleFav, TqUserPeopleFav, TvUserPeopleFav, UserPeopleFavMapper> implements UserPeopleFavService {
    @Override
    public List<TvUserPeopleFav> selectPriManagerFav(TsUserPeopleFav query) {
        return mapper.selectPriManagerFav(query);
    }
}
package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserOrgFavMapper;
import com.rong.user.module.entity.TbUserOrgFav;
import com.rong.user.module.query.TsUserOrgFav;
import com.rong.user.module.request.TqUserOrgFav;
import com.rong.user.module.view.TvUserOrgFav;
import com.rong.user.service.UserOrgFavService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrgFavServiceImpl extends BasicServiceImpl<TbUserOrgFav, TqUserOrgFav, TvUserOrgFav, UserOrgFavMapper> implements UserOrgFavService {
    @Override
    public List<TvUserOrgFav> selectPriOrgFav(TsUserOrgFav query) {
        return mapper.selectPriOrgFav(query);
    }
}
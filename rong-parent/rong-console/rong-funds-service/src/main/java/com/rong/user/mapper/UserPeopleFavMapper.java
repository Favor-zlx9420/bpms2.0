package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserPeopleFav;
import com.rong.user.module.query.TsUserPeopleFav;
import com.rong.user.module.view.TvUserPeopleFav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

import java.util.List;

public interface UserPeopleFavMapper extends CommonBasicMapper<TbUserPeopleFav, TvUserPeopleFav>, MultiTableMapper<TbUserPeopleFav, TvUserPeopleFav> {
    List<TvUserPeopleFav> selectPriManagerFav(TsUserPeopleFav query);
}
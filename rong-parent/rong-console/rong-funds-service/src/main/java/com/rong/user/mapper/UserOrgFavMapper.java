package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserOrgFav;
import com.rong.user.module.query.TsUserOrgFav;
import com.rong.user.module.view.TvUserOrgFav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

import java.util.List;

public interface UserOrgFavMapper extends CommonBasicMapper<TbUserOrgFav, TvUserOrgFav>, MultiTableMapper<TbUserOrgFav, TvUserOrgFav> {
    List<TvUserOrgFav> selectPriOrgFav(TsUserOrgFav query);
}
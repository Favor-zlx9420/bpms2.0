package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserProFav;
import com.rong.user.module.query.TsUserProFav;
import com.rong.user.module.view.TvUserProFav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

import java.util.List;

public interface UserProFavMapper extends CommonBasicMapper<TbUserProFav, TvUserProFav>, MultiTableMapper<TbUserProFav, TvUserProFav> {
    List<TvUserProFav> selectPriFundsFav(TsUserProFav query);
}
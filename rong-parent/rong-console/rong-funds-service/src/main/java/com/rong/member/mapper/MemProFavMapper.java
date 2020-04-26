package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemProFav;
import com.rong.member.module.view.TvMemProFav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemProFavMapper extends CommonBasicMapper<TbMemProFav, TvMemProFav>, MultiTableMapper<TbMemProFav, TvMemProFav> {
}
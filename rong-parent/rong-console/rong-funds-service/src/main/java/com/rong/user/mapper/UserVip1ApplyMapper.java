package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserVip1Apply;
import com.rong.user.module.view.TvUserVip1Apply;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserVip1ApplyMapper extends CommonBasicMapper<TbUserVip1Apply, TvUserVip1Apply>, MultiTableMapper<TbUserVip1Apply, TvUserVip1Apply> {
}
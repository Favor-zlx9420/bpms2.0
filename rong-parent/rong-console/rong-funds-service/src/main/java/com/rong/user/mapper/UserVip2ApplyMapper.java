package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserVip2Apply;
import com.rong.user.module.view.TvUserVip2Apply;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserVip2ApplyMapper extends CommonBasicMapper<TbUserVip2Apply, TvUserVip2Apply>, MultiTableMapper<TbUserVip2Apply, TvUserVip2Apply> {
}
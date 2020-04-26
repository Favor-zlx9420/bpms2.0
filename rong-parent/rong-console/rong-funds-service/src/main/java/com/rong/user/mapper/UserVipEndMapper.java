package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserVipEnd;
import com.rong.user.module.view.TvUserVipEnd;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserVipEndMapper extends CommonBasicMapper<TbUserVipEnd, TvUserVipEnd>, MultiTableMapper<TbUserVipEnd, TvUserVipEnd> {
}
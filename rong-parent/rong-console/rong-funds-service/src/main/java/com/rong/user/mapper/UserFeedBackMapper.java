package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserFeedBack;
import com.rong.user.module.view.TvUserFeedBack;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserFeedBackMapper extends CommonBasicMapper<TbUserFeedBack, TvUserFeedBack>, MultiTableMapper<TbUserFeedBack, TvUserFeedBack> {
}
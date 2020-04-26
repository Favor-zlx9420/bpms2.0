package com.rong.admin.mapper;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.view.TvAdmUser;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AdmUserMapper extends CommonBasicMapper<TbAdmUser, TvAdmUser>, MultiTableMapper<TbAdmUser, TvAdmUser> {
}
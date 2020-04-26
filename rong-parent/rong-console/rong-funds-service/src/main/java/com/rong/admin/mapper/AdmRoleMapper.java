package com.rong.admin.mapper;

import com.rong.admin.module.entity.TbAdmRole;
import com.rong.admin.module.view.TvAdmRole;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AdmRoleMapper extends CommonBasicMapper<TbAdmRole, TvAdmRole>, MultiTableMapper<TbAdmRole, TvAdmRole> {
}
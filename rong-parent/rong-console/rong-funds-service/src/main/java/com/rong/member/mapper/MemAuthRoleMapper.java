package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemAuthRole;
import com.rong.member.module.view.TvMemAuthRole;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemAuthRoleMapper extends CommonBasicMapper<TbMemAuthRole, TvMemAuthRole>, MultiTableMapper<TbMemAuthRole, TvMemAuthRole> {
}
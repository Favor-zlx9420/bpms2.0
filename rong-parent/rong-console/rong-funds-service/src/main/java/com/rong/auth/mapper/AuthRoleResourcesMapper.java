package com.rong.auth.mapper;

import com.rong.auth.module.entity.TbAuthRoleResources;
import com.rong.auth.module.view.TvAuthRoleResources;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AuthRoleResourcesMapper extends CommonBasicMapper<TbAuthRoleResources, TvAuthRoleResources>, MultiTableMapper<TbAuthRoleResources, TvAuthRoleResources> {
}
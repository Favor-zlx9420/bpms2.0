package com.rong.auth.mapper;

import com.rong.auth.module.entity.TbAuthRole;
import com.rong.auth.module.request.TqAuthRole;
import com.rong.auth.module.view.TvAuthRole;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

import java.util.List;

public interface AuthRoleMapper extends CommonBasicMapper<TbAuthRole, TvAuthRole>, MultiTableMapper<TbAuthRole, TvAuthRole> {
    List<TvAuthRole> getResourcesOfAuthRole(TqAuthRole req);
}
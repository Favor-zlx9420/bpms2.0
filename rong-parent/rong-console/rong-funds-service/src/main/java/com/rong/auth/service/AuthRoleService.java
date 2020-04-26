package com.rong.auth.service;

import com.rong.auth.module.entity.TbAuthRole;
import com.rong.auth.module.request.TqAuthRole;
import com.rong.auth.module.view.TvAuthRole;
import com.rong.common.service.BasicService;

import java.util.List;

public interface AuthRoleService extends BasicService<TbAuthRole, TqAuthRole, TvAuthRole> {
    List<TvAuthRole> getResourcesOfAuthRole(TqAuthRole req);
}
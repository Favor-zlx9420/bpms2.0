package com.rong.auth.service.impl;

import com.rong.auth.mapper.AuthRoleMapper;
import com.rong.auth.module.entity.TbAuthRole;
import com.rong.auth.module.request.TqAuthRole;
import com.rong.auth.module.view.TvAuthRole;
import com.rong.auth.service.AuthRoleService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthRoleServiceImpl extends BasicServiceImpl<TbAuthRole, TqAuthRole, TvAuthRole, AuthRoleMapper> implements AuthRoleService {
    @Override
    public List<TvAuthRole> getResourcesOfAuthRole(TqAuthRole req) {
        return mapper.getResourcesOfAuthRole(req);
    }
}
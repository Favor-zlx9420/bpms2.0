package com.rong.auth.service.impl;

import com.rong.auth.mapper.AuthRoleResourcesMapper;
import com.rong.auth.module.entity.TbAuthRoleResources;
import com.rong.auth.module.request.TqAuthRoleResources;
import com.rong.auth.module.view.TvAuthRoleResources;
import com.rong.auth.service.AuthRoleResourcesService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class AuthRoleResourcesServiceImpl extends BasicServiceImpl<TbAuthRoleResources, TqAuthRoleResources, TvAuthRoleResources, AuthRoleResourcesMapper> implements AuthRoleResourcesService {
}
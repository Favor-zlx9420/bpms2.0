package com.rong.auth.service.impl;

import com.rong.auth.mapper.AuthResourcesMapper;
import com.rong.auth.module.entity.TbAuthResources;
import com.rong.auth.module.request.TqAuthResources;
import com.rong.auth.module.view.TvAuthResources;
import com.rong.auth.service.AuthResourcesService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class AuthResourcesServiceImpl extends BasicServiceImpl<TbAuthResources, TqAuthResources, TvAuthResources, AuthResourcesMapper> implements AuthResourcesService {
}
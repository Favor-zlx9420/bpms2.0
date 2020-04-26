package com.rong.sys.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbOrgInfo;
import com.rong.sys.module.request.TqOrgInfo;
import com.rong.sys.module.view.TvOrgInfo;
import com.rong.sys.mapper.OrgInfoMapper;
import com.rong.sys.service.OrgInfoService;
import org.springframework.stereotype.Component;

@Component
public class OrgInfoServiceImpl extends BasicServiceImpl<TbOrgInfo, TqOrgInfo, TvOrgInfo, OrgInfoMapper> implements OrgInfoService {
}
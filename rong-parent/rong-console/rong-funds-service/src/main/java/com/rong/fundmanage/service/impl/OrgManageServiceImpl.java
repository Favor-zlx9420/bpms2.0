package com.rong.fundmanage.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.fundmanage.mapper.OrgManageMapper;
import com.rong.fundmanage.module.entity.TbOrgManage;
import com.rong.fundmanage.module.request.TqOrgManage;
import com.rong.fundmanage.module.view.TvOrgManage;
import com.rong.fundmanage.service.OrgManageService;
import org.springframework.stereotype.Service;

@Service
public class OrgManageServiceImpl extends BasicServiceImpl<TbOrgManage, TqOrgManage, TvOrgManage, OrgManageMapper> implements OrgManageService {
}
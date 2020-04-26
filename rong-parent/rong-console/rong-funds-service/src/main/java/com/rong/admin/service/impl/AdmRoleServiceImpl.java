package com.rong.admin.service.impl;

import com.rong.admin.mapper.AdmRoleMapper;
import com.rong.admin.module.entity.TbAdmRole;
import com.rong.admin.module.request.TqAdmRole;
import com.rong.admin.module.view.TvAdmRole;
import com.rong.admin.service.AdmRoleService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdmRoleServiceImpl extends BasicServiceImpl<TbAdmRole, TqAdmRole, TvAdmRole, AdmRoleMapper> implements AdmRoleService {
}
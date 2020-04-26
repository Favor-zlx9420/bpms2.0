package com.rong.admin.service.impl;

import com.rong.admin.mapper.AdmLogMapper;
import com.rong.admin.module.entity.TbAdmLog;
import com.rong.admin.module.request.TqAdmLog;
import com.rong.admin.module.view.TvAdmLog;
import com.rong.admin.service.AdmLogService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdmLogServiceImpl extends BasicServiceImpl<TbAdmLog, TqAdmLog, TvAdmLog, AdmLogMapper> implements AdmLogService {
}
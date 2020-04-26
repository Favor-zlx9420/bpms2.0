package com.rong.admin.service.impl;

import com.rong.admin.mapper.AdmMemorandumMapper;
import com.rong.admin.module.entity.TbAdmMemorandum;
import com.rong.admin.module.request.TqAdmMemorandum;
import com.rong.admin.module.view.TvAdmMemorandum;
import com.rong.admin.service.AdmMemorandumService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdmMemorandumServiceImpl extends BasicServiceImpl<TbAdmMemorandum, TqAdmMemorandum, TvAdmMemorandum, AdmMemorandumMapper> implements AdmMemorandumService {
}
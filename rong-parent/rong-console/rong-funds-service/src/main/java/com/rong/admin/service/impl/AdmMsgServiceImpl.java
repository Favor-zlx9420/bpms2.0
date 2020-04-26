package com.rong.admin.service.impl;

import com.rong.admin.mapper.AdmMsgMapper;
import com.rong.admin.module.entity.TbAdmMsg;
import com.rong.admin.module.request.TqAdmMsg;
import com.rong.admin.module.view.TvAdmMsg;
import com.rong.admin.service.AdmMsgService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdmMsgServiceImpl extends BasicServiceImpl<TbAdmMsg, TqAdmMsg, TvAdmMsg, AdmMsgMapper> implements AdmMsgService {
}
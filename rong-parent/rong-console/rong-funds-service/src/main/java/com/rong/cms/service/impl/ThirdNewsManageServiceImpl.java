package com.rong.cms.service.impl;

import com.rong.cms.mapper.ThirdNewsManageMapper;
import com.rong.cms.module.entity.TbThirdNewsManage;
import com.rong.cms.module.request.TqThirdNewsManage;
import com.rong.cms.module.view.TvThirdNewsManage;
import com.rong.cms.service.ThirdNewsManageService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ThirdNewsManageServiceImpl extends BasicServiceImpl<TbThirdNewsManage, TqThirdNewsManage, TvThirdNewsManage, ThirdNewsManageMapper> implements ThirdNewsManageService {
}
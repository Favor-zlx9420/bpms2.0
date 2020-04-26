package com.rong.cms.service.impl;

import com.rong.cms.module.entity.TbCmsDynProper;
import com.rong.cms.module.request.TqCmsDynProper;
import com.rong.cms.module.view.TvCmsDynProper;
import com.rong.cms.mapper.CmsDynProperMapper;
import com.rong.cms.service.CmsDynProperService;
import com.rong.common.service.impl.BasicServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CmsDynProperServiceImpl extends BasicServiceImpl<TbCmsDynProper, TqCmsDynProper, TvCmsDynProper, CmsDynProperMapper> implements CmsDynProperService {
}
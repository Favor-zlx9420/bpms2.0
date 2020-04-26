package com.rong.sys.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.sys.module.entity.TbLabel;
import com.rong.sys.module.request.TqLabel;
import com.rong.sys.module.view.TvLabel;
import com.rong.sys.mapper.LabelMapper;
import com.rong.sys.service.LabelService;
import org.springframework.stereotype.Component;

@Component
public class LabelServiceImpl extends BasicServiceImpl<TbLabel, TqLabel, TvLabel, LabelMapper> implements LabelService {
}
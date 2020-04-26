package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserLabelMapper;
import com.rong.user.module.entity.TbUserLabel;
import com.rong.user.module.request.TqUserLabel;
import com.rong.user.module.view.TvUserLabel;
import com.rong.user.service.UserLabelService;
import org.springframework.stereotype.Service;

@Service
public class UserLabelServiceImpl extends BasicServiceImpl<TbUserLabel, TqUserLabel, TvUserLabel, UserLabelMapper> implements UserLabelService {
}
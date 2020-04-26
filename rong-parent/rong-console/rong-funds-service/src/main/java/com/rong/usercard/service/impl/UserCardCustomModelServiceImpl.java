package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardCustomModelMapper;
import com.rong.usercard.module.entity.TbUserCardCustomModel;
import com.rong.usercard.module.request.TqUserCardCustomModel;
import com.rong.usercard.module.view.TvUserCardCustomModel;
import com.rong.usercard.service.UserCardCustomModelService;
import org.springframework.stereotype.Service;

@Service
public class UserCardCustomModelServiceImpl extends BasicServiceImpl<TbUserCardCustomModel, TqUserCardCustomModel, TvUserCardCustomModel, UserCardCustomModelMapper> implements UserCardCustomModelService {
}
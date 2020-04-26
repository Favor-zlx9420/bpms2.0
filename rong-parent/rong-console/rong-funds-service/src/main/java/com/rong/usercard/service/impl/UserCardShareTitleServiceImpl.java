package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardShareTitleMapper;
import com.rong.usercard.module.entity.TbUserCardShareTitle;
import com.rong.usercard.module.request.TqUserCardShareTitle;
import com.rong.usercard.module.view.TvUserCardShareTitle;
import com.rong.usercard.service.UserCardShareTitleService;
import org.springframework.stereotype.Service;

@Service
public class UserCardShareTitleServiceImpl extends BasicServiceImpl<TbUserCardShareTitle, TqUserCardShareTitle, TvUserCardShareTitle, UserCardShareTitleMapper> implements UserCardShareTitleService {
}
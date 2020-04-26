package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardTalkMapper;
import com.rong.usercard.module.entity.TbUserCardTalk;
import com.rong.usercard.module.request.TqUserCardTalk;
import com.rong.usercard.module.view.TvUserCardTalk;
import com.rong.usercard.service.UserCardTalkService;
import org.springframework.stereotype.Service;

@Service
public class UserCardTalkServiceImpl extends BasicServiceImpl<TbUserCardTalk, TqUserCardTalk, TvUserCardTalk, UserCardTalkMapper> implements UserCardTalkService {
}
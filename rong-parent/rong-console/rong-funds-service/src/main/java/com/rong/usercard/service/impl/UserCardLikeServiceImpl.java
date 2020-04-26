package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardLikeMapper;
import com.rong.usercard.module.entity.TbUserCardLike;
import com.rong.usercard.module.request.TqUserCardLike;
import com.rong.usercard.module.view.TvUserCardLike;
import com.rong.usercard.service.UserCardLikeService;
import org.springframework.stereotype.Service;

@Service
public class UserCardLikeServiceImpl extends BasicServiceImpl<TbUserCardLike, TqUserCardLike, TvUserCardLike, UserCardLikeMapper> implements UserCardLikeService {
}
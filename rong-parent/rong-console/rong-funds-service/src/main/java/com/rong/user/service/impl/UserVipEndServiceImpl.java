package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserVipEndMapper;
import com.rong.user.module.entity.TbUserVipEnd;
import com.rong.user.module.request.TqUserVipEnd;
import com.rong.user.module.view.TvUserVipEnd;
import com.rong.user.service.UserVipEndService;
import org.springframework.stereotype.Service;

@Service
public class UserVipEndServiceImpl extends BasicServiceImpl<TbUserVipEnd, TqUserVipEnd, TvUserVipEnd, UserVipEndMapper> implements UserVipEndService {
}
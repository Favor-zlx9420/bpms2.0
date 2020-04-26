package com.rong.usercard.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.usercard.mapper.UserCardInfoMapper;
import com.rong.usercard.module.entity.TbUserCardInfo;
import com.rong.usercard.module.request.TqUserCardInfo;
import com.rong.usercard.module.view.TvUserCardInfo;
import com.rong.usercard.service.UserCardInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserCardInfoServiceImpl extends BasicServiceImpl<TbUserCardInfo, TqUserCardInfo, TvUserCardInfo, UserCardInfoMapper> implements UserCardInfoService {
}
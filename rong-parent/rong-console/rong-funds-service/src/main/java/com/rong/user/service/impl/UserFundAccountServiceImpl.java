package com.rong.user.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.user.mapper.UserFundAccountMapper;
import com.rong.user.module.entity.TbUserFundAccount;
import com.rong.user.module.request.TqUserFundAccount;
import com.rong.user.module.view.TvUserFundAccount;
import com.rong.user.service.UserFundAccountService;
import org.springframework.stereotype.Service;

@Service
public class UserFundAccountServiceImpl extends BasicServiceImpl<TbUserFundAccount, TqUserFundAccount, TvUserFundAccount, UserFundAccountMapper> implements UserFundAccountService {
}
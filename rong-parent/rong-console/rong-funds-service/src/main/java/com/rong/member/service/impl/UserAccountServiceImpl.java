package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbUserAccount;
import com.rong.member.module.request.TqUserAccount;
import com.rong.member.module.view.TvUserAccount;
import com.rong.member.mapper.UserAccountMapper;
import com.rong.member.service.UserAccountService;
import org.springframework.stereotype.Component;

@Component
public class UserAccountServiceImpl extends BasicServiceImpl<TbUserAccount, TqUserAccount, TvUserAccount, UserAccountMapper> implements UserAccountService {
}
package com.rong.member.service;

import com.rong.common.service.BasicService;
import com.rong.member.module.entity.TbUserAccount;
import com.rong.member.module.request.TqUserAccount;
import com.rong.member.module.view.TvUserAccount;

public interface UserAccountService extends BasicService<TbUserAccount, TqUserAccount, TvUserAccount> {
}
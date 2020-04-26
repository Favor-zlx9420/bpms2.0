package com.rong.user.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.user.module.entity.TbUserFundAccount;
import com.rong.user.module.view.TvUserFundAccount;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserFundAccountMapper extends CommonBasicMapper<TbUserFundAccount, TvUserFundAccount>, MultiTableMapper<TbUserFundAccount, TvUserFundAccount> {
}
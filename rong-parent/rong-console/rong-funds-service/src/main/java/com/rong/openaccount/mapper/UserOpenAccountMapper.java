package com.rong.openaccount.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.openaccount.module.entity.TbUserOpenAccount;
import com.rong.openaccount.module.view.TvUserOpenAccount;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserOpenAccountMapper extends CommonBasicMapper<TbUserOpenAccount, TvUserOpenAccount>, MultiTableMapper<TbUserOpenAccount, TvUserOpenAccount> {
}
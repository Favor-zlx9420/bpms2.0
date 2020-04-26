package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbUserAccount;
import com.rong.member.module.request.TqUserAccount;
import com.rong.member.module.view.TvUserAccount;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface UserAccountMapper extends CommonBasicMapper<TbUserAccount, TvUserAccount>, MultiTableMapper<TbUserAccount, TvUserAccount> {
    int updateAccountWithPessimistic(TqUserAccount req);
}
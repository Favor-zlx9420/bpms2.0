package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemBankcard;
import com.rong.member.module.view.TvMemBankcard;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemBankcardMapper extends CommonBasicMapper<TbMemBankcard, TvMemBankcard>, MultiTableMapper<TbMemBankcard, TvMemBankcard> {
}
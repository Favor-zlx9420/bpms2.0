package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemPersonalCreditfile;
import com.rong.member.module.view.TvMemPersonalCreditfile;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemPersonalCreditfileMapper extends CommonBasicMapper<TbMemPersonalCreditfile, TvMemPersonalCreditfile>, MultiTableMapper<TbMemPersonalCreditfile, TvMemPersonalCreditfile> {
}
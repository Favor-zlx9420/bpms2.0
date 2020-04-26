package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemCreditHistory;
import com.rong.member.module.view.TvMemCreditHistory;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemCreditHistoryMapper extends CommonBasicMapper<TbMemCreditHistory, TvMemCreditHistory>, MultiTableMapper<TbMemCreditHistory, TvMemCreditHistory> {
}
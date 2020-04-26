package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemCreditfileCertifyLog;
import com.rong.member.module.view.TvMemCreditfileCertifyLog;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemCreditfileCertifyLogMapper extends CommonBasicMapper<TbMemCreditfileCertifyLog, TvMemCreditfileCertifyLog>, MultiTableMapper<TbMemCreditfileCertifyLog, TvMemCreditfileCertifyLog> {
}
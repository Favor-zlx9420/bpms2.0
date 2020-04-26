package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemCustomer;
import com.rong.member.module.view.TvMemCustomer;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemCustomerMapper extends CommonBasicMapper<TbMemCustomer, TvMemCustomer>, MultiTableMapper<TbMemCustomer, TvMemCustomer> {
}
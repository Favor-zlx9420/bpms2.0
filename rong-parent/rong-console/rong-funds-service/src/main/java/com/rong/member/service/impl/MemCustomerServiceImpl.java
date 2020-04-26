package com.rong.member.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.member.module.entity.TbMemCustomer;
import com.rong.member.module.request.TqMemCustomer;
import com.rong.member.module.view.TvMemCustomer;
import com.rong.member.mapper.MemCustomerMapper;
import com.rong.member.service.MemCustomerService;
import com.vitily.mybatis.core.wrapper.query.MultiTableIdWrapper;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.springframework.stereotype.Component;

@Component
public class MemCustomerServiceImpl extends BasicServiceImpl<TbMemCustomer, TqMemCustomer, TvMemCustomer, MemCustomerMapper> implements MemCustomerService {

    @Override
    public MultiTableQueryWrapper getMultiCommonWrapper() {
        return new MultiTableQueryWrapper()
                .selectAllFiels(true)
                ;
    }

    @Override
    public MultiTableIdWrapper getMultiCommonIdWrapper(Object id) {
        return MultiTableIdWrapper.valueOf(id, getMultiCommonWrapper());
    }
}
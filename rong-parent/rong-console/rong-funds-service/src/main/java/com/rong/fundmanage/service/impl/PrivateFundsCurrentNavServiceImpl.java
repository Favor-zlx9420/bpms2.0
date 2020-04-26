package com.rong.fundmanage.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.fundmanage.mapper.PrivateFundsCurrentNavMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import com.rong.fundmanage.module.request.TqPrivateFundsCurrentNav;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentNav;
import com.rong.fundmanage.service.PrivateFundsCurrentNavService;
import org.springframework.stereotype.Service;

@Service
public class PrivateFundsCurrentNavServiceImpl extends BasicServiceImpl<TbPrivateFundsCurrentNav, TqPrivateFundsCurrentNav, TvPrivateFundsCurrentNav, PrivateFundsCurrentNavMapper> implements PrivateFundsCurrentNavService {
    @Override
    public void resetNav() {
        mapper.resetNavTemp();
        if (mapper.resetNavTempCount() > 0) {
            mapper.resetNav();
        }
    }
}
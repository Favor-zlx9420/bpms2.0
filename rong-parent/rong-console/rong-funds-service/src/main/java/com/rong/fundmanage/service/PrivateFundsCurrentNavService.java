package com.rong.fundmanage.service;

import com.rong.common.service.BasicService;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import com.rong.fundmanage.module.request.TqPrivateFundsCurrentNav;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentNav;

public interface PrivateFundsCurrentNavService extends BasicService<TbPrivateFundsCurrentNav, TqPrivateFundsCurrentNav, TvPrivateFundsCurrentNav> {
    void resetNav();
}
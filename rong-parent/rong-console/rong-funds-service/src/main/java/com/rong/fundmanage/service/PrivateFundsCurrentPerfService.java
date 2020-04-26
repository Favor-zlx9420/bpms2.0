package com.rong.fundmanage.service;

import com.rong.common.service.BasicService;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentPerf;
import com.rong.fundmanage.module.request.TqPrivateFundsCurrentPerf;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentPerf;

public interface PrivateFundsCurrentPerfService extends BasicService<TbPrivateFundsCurrentPerf, TqPrivateFundsCurrentPerf, TvPrivateFundsCurrentPerf> {
    void resetPerf();
}
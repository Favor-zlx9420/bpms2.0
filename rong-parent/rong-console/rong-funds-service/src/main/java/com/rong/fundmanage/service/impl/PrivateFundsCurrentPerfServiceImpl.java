package com.rong.fundmanage.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.fundmanage.mapper.PrivateFundsCurrentPerfMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentPerf;
import com.rong.fundmanage.module.request.TqPrivateFundsCurrentPerf;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentPerf;
import com.rong.fundmanage.service.PrivateFundsCurrentPerfService;
import org.springframework.stereotype.Service;

@Service
public class PrivateFundsCurrentPerfServiceImpl extends BasicServiceImpl<TbPrivateFundsCurrentPerf, TqPrivateFundsCurrentPerf, TvPrivateFundsCurrentPerf, PrivateFundsCurrentPerfMapper> implements PrivateFundsCurrentPerfService {
    @Override
    public void resetPerf() {
        mapper.resetPerfTemp();
        if (mapper.resetPerfTempCount() > 0) {
            mapper.resetPerf();
        }
    }
}
package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentPerf;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentPerf;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PrivateFundsCurrentPerfMapper extends CommonBasicMapper<TbPrivateFundsCurrentPerf, TvPrivateFundsCurrentPerf>, MultiTableMapper<TbPrivateFundsCurrentPerf, TvPrivateFundsCurrentPerf> {
    void resetPerfTemp();

    Integer resetPerfTempCount();

    void resetPerf();
}
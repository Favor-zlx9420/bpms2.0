package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentPerformance;
import com.rong.fundmanage.module.view.TvRaisedFundCurrentPerformance;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface RaisedFundCurrentPerformanceMapper extends CommonBasicMapper<TbRaisedFundCurrentPerformance, TvRaisedFundCurrentPerformance>, MultiTableMapper<TbRaisedFundCurrentPerformance, TvRaisedFundCurrentPerformance> {
    void resetPerformanceTemp();

    Integer resetPerformanceTempCount();

    void resetPerformance();
}
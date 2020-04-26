package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentGr;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentGr;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

public interface PrivateFundsCurrentGrMapper extends CommonBasicMapper<TbPrivateFundsCurrentGr, TvPrivateFundsCurrentGr>, MultiTableMapper<TbPrivateFundsCurrentGr, TvPrivateFundsCurrentGr> {
    void resetGrTemp();

    Integer resetGrTempCount();

    void resetGr();

    void resetYearTemp(@Param("y1")String y1, @Param("y2")String y2, @Param("y3")String y3, @Param("y4")String y4, @Param("y5")String y5, @Param("y6")String y6, @Param("y7")String y7, @Param("y8")String y8, @Param("nowY")String nowY);

    Integer resetYearTempCount();

    void resetYear();

    void restCompanyRankingTemp();

    Integer restCompanyRankingTempCount();

    void restCompanyRanking();

    void restManagerRankingTemp();

    Integer restManagerRankingTempCount();

    void restManagerRanking();

    void restPfundRankingTemp();

    Integer restPfundRankingTempCount();

    void restPfundRanking();

    void resetPrivatePerfTemp();

    Integer resetPrivatePerfTempCount();

    void resetPrivatePerf();

    void deleteNewsSummary();
}
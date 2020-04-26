package com.rong.tong.fund.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.fund.module.view.TvFundClass;
import com.rong.tong.pfunds.module.view.*;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FundClassMapper extends CommonBasicMapper<TbFundClass, TvFundClass>, MultiTableMapper<TbFundClass, TvFundClass> {
    int count(@Param("key") String key);

    List<TvSearchPfundInfo> selectSearchFundClassInfoList(@Param("limitStart")Integer limitStart, @Param("limitEnd")Integer limitEnd, @Param("key") String key, @Param("orderBy") String orderBy, @Param("userId") Long userId);

    TvProduceSummary getFundInfoSummary(@Param("securityId") Integer securityId,@Param("userId") Long userId);

    List<TvHisNav> hisNav(@Param("limitStart")Integer limitStart, @Param("limitEnd")Integer limitEnd, @Param("securityId") Integer securityId);

    Integer hisNavCount(@Param("securityId") Integer securityId);

    TvDateBoundary hisNavDateBoundary(@Param("securityId") Integer securityId);

    List<TvHisNav> accumNavTrend(@Param("startDate") Date startDate, @Param("endDate")Date endDate, @Param("securityId") Integer securityId);

    List<TvMaxDrawdownTrend> maxDrawdownTrend(@Param("securityId") Integer securityId);

    TvMaxDrawdownTrend maxHisDrawdown(@Param("securityId") Integer securityId);

    TvIntervalReturn getIntervalReturn(@Param("securityId") Integer securityId);

    List<TvYearMonReturn> getYearReturn(@Param("securityId") Integer securityId);

    List<TvYearMonReturn> getMonReturn(@Param("securityId") Integer securityId);

    List<TvRiskAssessment> riskAssessment(@Param("securityId") Integer securityId);

    List<TvFundStyle> fundStyle(@Param("securityId") Integer securityId);

    TvProduceInformation produceInformation(@Param("securityId") Integer securityId);
}
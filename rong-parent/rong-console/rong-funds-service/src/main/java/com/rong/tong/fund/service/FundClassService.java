package com.rong.tong.fund.service;

import com.rong.common.module.TvPageList;
import com.rong.common.service.FundsBasicService;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.fund.module.request.TqFundClass;
import com.rong.tong.fund.module.view.TvFundClass;
import com.rong.tong.pfunds.module.view.*;
import com.vitily.mybatis.core.wrapper.PageInfo;

import java.util.Date;
import java.util.List;

public interface FundClassService extends FundsBasicService<TbFundClass, TqFundClass, TvFundClass> {
    int count(String key);

    TvPageList<TvSearchPfundInfo> getSearchFundClassInfo(PageInfo pageInfo, String key, Long userId);

    TvProduceSummary getFundInfoSummary(Integer securityId, Long userId);

    TvPageList<TvHisNav> hisNav(PageInfo pageInfo, Integer securityId);

    TvDateBoundary hisNavDateBoundary(Integer securityId);

    List<TvHisNav> accumNavTrend(Date startDate, Date endDate, Integer securityId);

    List<TvMaxDrawdownTrend> maxDrawdownTrend(Integer securityId);

    TvMaxDrawdownTrend maxHisDrawdown(Integer securityId);

    TvIntervalReturn getIntervalReturn(Integer securityId);

    List<TvYearMonReturn> getYearReturn(Integer securityId);

    List<TvYearMonReturn> getMonReturn(Integer securityId);

    List<TvRiskAssessment> riskAssessment(Integer securityId);

    List<TvFundStyle> fundStyle(Integer securityId);

    TvProduceInformation produceInformation(Integer securityId);
}
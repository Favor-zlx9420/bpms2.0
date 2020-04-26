package com.rong.tong.pfunds.service;

import com.rong.common.consts.BusinessEnumContainer;
import com.rong.common.module.TvPageList;
import com.rong.tong.pfunds.module.request.TqCompanyRanking;
import com.rong.tong.pfunds.module.request.TqManagerRanking;
import com.rong.tong.pfunds.module.request.TqRanking;
import com.rong.tong.pfunds.module.request.TqSuperProduce;
import com.rong.tong.pfunds.module.view.*;
import com.vitily.mybatis.core.wrapper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BusinessService {
    Map<Integer,Integer> searchIndex(String key);

    List<TvHostSearch> hostSearch();

    List<TvHostSearch> hostSearchFull();

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

    TvCompanySummary companySummary(Integer partyId);

    List<TvSearchPfundInfo> allProduce(Integer partyId);

    TvPageList<TvRanking> ranking(TqRanking req);

    TvPageList<TvRankingSimple> rankingSimple(TqRanking req);

    TvPageList<TvSuperProduce> superProduce(TqSuperProduce req);

    TvPageList<TvCompanyRanking> companyRanking(TqCompanyRanking req);

    TvPageList<TvManagerRanking> managerRanking(TqManagerRanking req);

    BusinessEnumContainer.FundType getFundType(Integer securityId);

    List<TvPfundMktList> pfundMktList();

    List<TvPfundMktHisNav> pfundMktHis(Date startDate, Date endDate, Integer securityId);
}

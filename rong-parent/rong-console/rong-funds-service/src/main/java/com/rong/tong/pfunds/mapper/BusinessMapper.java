package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.request.TqCompanyRanking;
import com.rong.tong.pfunds.module.request.TqManagerRanking;
import com.rong.tong.pfunds.module.request.TqRanking;
import com.rong.tong.pfunds.module.request.TqSuperProduce;
import com.rong.tong.pfunds.module.view.*;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-15 20:56
 **/
@Resource
public interface BusinessMapper extends CommonBasicMapper, MultiTableMapper {

    List<TvHostSearch> hostSearch();

    List<TvHostSearch> hostSearchFull();

    TvProduceSummary getFundInfoSummary(@Param("securityId") Integer securityId,@Param("userId") Long userId);

    List<TvHisNav> hisNav(@Param("limitStart")Integer limitStart, @Param("limitEnd")Integer limitEnd, @Param("securityId") Integer securityId);

    Integer hisNavCount(@Param("securityId") Integer securityId);

    TvDateBoundary hisNavDateBoundary(@Param("securityId") Integer securityId);

    List<TvHisNav> accumNavTrend(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("securityId") Integer securityId);

    List<TvMaxDrawdownTrend> maxDrawdownTrend(@Param("securityId") Integer securityId, @Param("window") Integer window);

    TvMaxDrawdownTrend maxHisDrawdown(@Param("securityId") Integer securityId, @Param("window") Integer window);

    TvIntervalReturn getIntervalReturn(@Param("securityId") Integer securityId);

    List<TvYearMonReturn> getYearReturn(@Param("securityId") Integer securityId);

    List<TvYearMonReturn> getMonReturn(@Param("securityId") Integer securityId);

    List<TvRiskAssessment> riskAssessment(@Param("securityId") Integer securityId);

    List<TvFundStyle> fundStyle(@Param("securityId") Integer securityId, @Param("window") Integer window);

    TvProduceInformation produceInformation(@Param("securityId") Integer securityId);

    TvCompanySummary companySummary(@Param("partyId") Integer partyId);

    List<TvSearchPfundInfo> allProduce(@Param("partyId") Integer partyId);

    Integer rankingCount(TqRanking req);

    List<TvRanking> rankingInterval(TqRanking req);

    List<TvRanking> rankingYear(TqRanking req);

    List<TvRankingSimple> rankingSimple(TqRanking req);

    Integer superProduceCount(TqSuperProduce req);

    List<TvSuperProduce> superProduce(TqSuperProduce req);

    Integer companyRankingCount(TqCompanyRanking req);

    List<TvCompanyRanking> companyRanking(TqCompanyRanking req);

    Integer managerRankingCount(TqManagerRanking req);

    List<TvManagerRanking> managerRanking(TqManagerRanking req);

    Integer countPfundBySecurityId(@Param("securityId") Integer securityId);

    List<TvPfundMktList> pfundMktList();

    List<TvPfundMktHisNav> pfundMktHis(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("securityId") Integer securityId);

    String getReturnByDate(@Param("startDate")Date startDate, @Param("securityId") Integer securityId);

    Boolean getFav(@Param("securityId") Integer securityId, @Param("userId") Long userId);

    String getRegCityCd(@Param("name") String name);
}

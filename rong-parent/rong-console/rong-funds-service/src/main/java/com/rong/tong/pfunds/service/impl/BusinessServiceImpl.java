package com.rong.tong.pfunds.service.impl;

import com.rong.common.consts.BusinessEnumContainer;
import com.rong.common.module.TvPageList;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.tong.fund.service.FundClassService;
import com.rong.tong.pfunds.module.request.TqCompanyRanking;
import com.rong.tong.pfunds.module.request.TqManagerRanking;
import com.rong.tong.pfunds.module.request.TqRanking;
import com.rong.tong.pfunds.module.request.TqSuperProduce;
import com.rong.tong.pfunds.module.view.*;
import com.rong.tong.pfunds.mapper.BusinessMapper;
import com.rong.tong.pfunds.service.*;
import com.vitily.mybatis.core.wrapper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-14 17:15
 **/
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Autowired
    private PfundService pfundService;

    @Autowired
    private PfundInstInfoService pfundInstInfoService;

    @Autowired
    private PfundManagerService pfundManagerService;

    @Autowired
    private FundClassService fundClassService;

    @Autowired
    private MdInstitutionService mdInstitutionService;

    @Autowired
    private MdPeopleServiceImpl mdPeopleService;

    @Override
    public Map<Integer,Integer> searchIndex(String key) {
        //拼接模糊查询
        key = "%"+key+"%";
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        hashMap.put(BusinessEnumContainer.SearchType.私募产品.getValue(),pfundService.count(key));
        hashMap.put(BusinessEnumContainer.SearchType.公募.getValue(),fundClassService.count(key));
        hashMap.put(BusinessEnumContainer.SearchType.公司.getValue(),pfundInstInfoService.count(key));
        hashMap.put(BusinessEnumContainer.SearchType.经理.getValue(),pfundManagerService.count(key));
        return hashMap;
    }

    @Override
    public List<TvHostSearch> hostSearch() {
        return businessMapper.hostSearch();
    }

    @Override
    public List<TvHostSearch> hostSearchFull() {
        return businessMapper.hostSearchFull();
    }

    @Override
    public TvProduceSummary getFundInfoSummary(Integer securityId, Long userId) {
        TvProduceSummary fundInfoSummary = businessMapper.getFundInfoSummary(securityId, userId);
        fundInfoSummary.setInvestStrategy(BusinessEnumContainer.InvestStrategyType.getDesc(Integer.valueOf(fundInfoSummary.getInvestStrategy())));
        fundInfoSummary.setStatus(BusinessEnumContainer.StatusType.getDesc(Integer.valueOf(fundInfoSummary.getStatus())));
        fundInfoSummary.setManagers(mdPeopleService.getListByPfundSecurityId(securityId));
        return fundInfoSummary;
    }

    @Override
    public TvPageList<TvHisNav> hisNav(PageInfo page, Integer securityId) {
        if(page == null){
            throw new RuntimeException("page entity can not be null !");
        }
        TvPageList<TvHisNav> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(page.getPageIndex(),page.getPageSize());
        pageInfo.setRecordCount(businessMapper.hisNavCount(securityId));
        pageList.setPageInfo(pageInfo);
        pageList.setList(businessMapper.hisNav(pageInfo.getPageSize() * (pageInfo.getPageIndex()-1),pageInfo.getPageSize(), securityId));
        return pageList;
    }

    @Override
    public TvDateBoundary hisNavDateBoundary(Integer securityId) {
        return businessMapper.hisNavDateBoundary(securityId);
    }

    @Override
    public List<TvHisNav> accumNavTrend(Date startDate, Date endDate, Integer securityId) {
        return businessMapper.accumNavTrend(startDate, endDate, securityId);
    }

    @Override
    public  List<TvMaxDrawdownTrend> maxDrawdownTrend(Integer securityId) {
        List<TvMaxDrawdownTrend> result = businessMapper.maxDrawdownTrend(securityId, 1);
        //近一个月数据没有找近三个月数据
        if (result.size()  == 0) {
            result = businessMapper.maxDrawdownTrend(securityId, 2);
        }
        return result;
    }

    @Override
    public  TvMaxDrawdownTrend maxHisDrawdown(Integer securityId) {
        TvMaxDrawdownTrend result = businessMapper.maxHisDrawdown(securityId, 1);
        if (result == null){
            result = businessMapper.maxHisDrawdown(securityId, 2);
        }
        return result;
    }

    @Override
    public TvIntervalReturn getIntervalReturn(Integer securityId) {
        return businessMapper.getIntervalReturn(securityId);
    }

    @Override
    public List<TvYearMonReturn> getYearReturn(Integer securityId) {
        return businessMapper.getYearReturn(securityId);
    }

    @Override
    public List<TvYearMonReturn> getMonReturn(Integer securityId) {
        return businessMapper.getMonReturn(securityId);
    }

    @Override
    public List<TvRiskAssessment> riskAssessment(Integer securityId) {
        List<TvRiskAssessment> tvRiskAssessments = businessMapper.riskAssessment(securityId);
        tvRiskAssessments.stream().forEach(
                item -> {
                    item.setWindow(BusinessEnumContainer.WindowType.getDesc(Integer.valueOf(item.getWindow())));
                }
        );
        return tvRiskAssessments;
    }

    @Override
    public List<TvFundStyle> fundStyle(Integer securityId) {
        List<TvFundStyle> result = businessMapper.fundStyle(securityId, 1);
        //近一个月没数据取近三个月数据
        if (result.size() == 0) {
            result = businessMapper.fundStyle(securityId, 2);
        }
        return result;
    }

    @Override
    public TvProduceInformation produceInformation(Integer securityId) {
        return businessMapper.produceInformation(securityId);
    }

    @Override
    public TvCompanySummary companySummary(Integer partyId) {
        return businessMapper.companySummary(partyId);
    }

    @Override
    public List<TvSearchPfundInfo> allProduce(Integer partyId) {
        List<TvSearchPfundInfo> tvSearchPfundInfos = businessMapper.allProduce(partyId);
        tvSearchPfundInfos.stream().forEach(
                item -> {
                    TvSearchPfundInfo navDetail = pfundService.getNavDetail(item.getSecurityId());
                    if (navDetail != null) {
                        item.setNavDetail(navDetail);
                    }
                }
        );
        return tvSearchPfundInfos;
    }

    @Override
    public TvPageList<TvRanking> ranking(TqRanking req) {
        if(req.getPageInfo() == null){
            req.setPageInfo(new PageInfo());
        }
        if (StringUtil.isNotEmpty(req.getKey())) {
            req.setKey("%"+req.getKey()+"%");
        }
        if (StringUtil.isNotEmpty(req.getRegCityStr())) {
            req.setRegCity(businessMapper.getRegCityCd("%"+req.getRegCityStr()+"%"));
        }
        if (StringUtil.isNotEmpty(req.getRegProvinceStr())) {
            req.setRegProvince(businessMapper.getRegCityCd("%"+req.getRegProvinceStr()+"%"));
        }
        //1近一个月2近三个月3最近半年4最近一年5最近两年6最近三年7最近五年8成产以来9今年以来
        //配置查询时间
        if (StringUtil.isNotEmpty(req.getSearchInterval())) {
            switch(req.getSearchInterval())
            {
                case "1" :
                    req.setEndDate(DateUtil.addMonth(new Date(), -1));
                    break;
                case "2" :
                    req.setEndDate(DateUtil.addMonth(new Date(), -3));
                    break;
                case "3" :
                    req.setEndDate(DateUtil.addMonth(new Date(), -6));
                    break;
                case "4" :
                    req.setEndDate(DateUtil.addYear(new Date(), -1));
                    break;
                case "5" :
                    req.setEndDate(DateUtil.addYear(new Date(), -2));
                    break;
                case "6" :
                    req.setEndDate(DateUtil.addYear(new Date(), -3));
                    break;
                case "7" :
                    req.setEndDate(DateUtil.addYear(new Date(), -5));
                    break;
                case "9" :
                    Calendar calendar = Calendar.getInstance();
                    calendar.clear();
                    calendar.set(Calendar.YEAR, DateUtil.getYear(new Date()));
                    req.setEndDate(calendar.getTime());
                    break;
            }
        }
        if (StringUtil.isNotEmpty(req.getSearchYear()) && req.getSearchYear().equals("9")) {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, DateUtil.getYear(new Date()));
            req.setEndDate(calendar.getTime());
        }
        TvPageList<TvRanking> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(req.getPageInfo().getPageIndex(),req.getPageInfo().getPageSize());
        pageInfo.setRecordCount(businessMapper.rankingCount(req));
        pageList.setPageInfo(pageInfo);
        req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1));
        req.setLimitEnd(pageInfo.getPageSize());
        //年度收益
        if (req.getSearchType().equals(BusinessEnumContainer.RankingSearchTyp.年度收益.getValue())) {
            if (req.getPageInfo().getSortField() != null) {
                req.setOrderBy(BusinessEnumContainer.RankingYearOrderBy.getDesc(req.getPageInfo().getSortField()) + " " + req.getPageInfo().getSortDistanct());
            }
            List<TvRanking> tvRankings = businessMapper.rankingYear(req);
            tvRankings.stream().forEach(
                    item -> {
                        Boolean fav = businessMapper.getFav(item.getSecurityId(), req.getLoginUserId());
                        if (fav != null) {
                            item.setFav(fav);
                        }
                    }
            );
            pageList.setList(tvRankings);
            return pageList;
        }
        if (StringUtil.isNotEmpty(req.getPageInfo().getSortField())) {
            req.setOrderBy(BusinessEnumContainer.RankingIntervalOrderBy.getDesc(req.getPageInfo().getSortField()) + " " + req.getPageInfo().getSortDistanct());
        }
        List<TvRanking> tvRankings = businessMapper.rankingInterval(req);
        tvRankings.stream().forEach(
                item -> {
                    Boolean fav = businessMapper.getFav(item.getSecurityId(), req.getLoginUserId());
                    if (fav != null) {
                        item.setFav(fav);
                    }
                }
        );
        pageList.setList(tvRankings);
        return pageList;
    }

    @Override
    public TvPageList<TvRankingSimple> rankingSimple(TqRanking req) {
        if(req.getPageInfo() == null){
            req.setPageInfo(new PageInfo());
        }
        //1近一个月2近三个月3最近半年4最近一年5最近两年6最近三年7最近五年8成产以来9今年以来
        //配置查询时间
        if (StringUtil.isNotEmpty(req.getSearchInterval())) {
            switch(req.getSearchInterval())
            {
                case "1" :
                    req.setEndDate(DateUtil.addMonth(new Date(), -1));
                    break;
                case "2" :
                    req.setEndDate(DateUtil.addMonth(new Date(), -3));
                    break;
                case "3" :
                    req.setEndDate(DateUtil.addMonth(new Date(), -6));
                    break;
                case "4" :
                    req.setEndDate(DateUtil.addYear(new Date(), -1));
                    break;
                case "5" :
                    req.setEndDate(DateUtil.addYear(new Date(), -2));
                    break;
                case "6" :
                    req.setEndDate(DateUtil.addYear(new Date(), -3));
                    break;
                case "7" :
                    req.setEndDate(DateUtil.addYear(new Date(), -5));
                    break;
                case "9" :
                    Calendar calendar = Calendar.getInstance();
                    calendar.clear();
                    calendar.set(Calendar.YEAR, DateUtil.getYear(new Date()));
                    req.setEndDate(calendar.getTime());
                    break;
            }
        }
        TvPageList<TvRankingSimple> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(req.getPageInfo().getPageIndex(),req.getPageInfo().getPageSize());
        pageInfo.setRecordCount(businessMapper.rankingCount(req));
        pageList.setPageInfo(pageInfo);
        req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1));
        req.setLimitEnd(pageInfo.getPageSize());
        //设置默认排序
        if (StringUtil.isEmpty(req.getPageInfo().getSortField())) {
            req.getPageInfo().setSortField(BusinessEnumContainer.CompanyRankingOrderBy.returnRate.getValue());
            req.getPageInfo().setSortDistanct("DESC");
        }
        req.setOrderBy(BusinessEnumContainer.CompanyRankingOrderBy.getDesc(req.getPageInfo().getSortField(),
                BusinessEnumContainer.RankingSimpleSuffix.getDesc(req.getSearchInterval())) + " " + req.getPageInfo().getSortDistanct());
        List<TvRankingSimple> tvRankingSimples = businessMapper.rankingSimple(req);
        pageList.setList(tvRankingSimples);
        return pageList;
    }

    @Override
    public TvPageList<TvSuperProduce> superProduce(TqSuperProduce req) {
        if(req.getPageInfo() == null){
            req.setPageInfo(new PageInfo());
        }
        TvPageList<TvSuperProduce> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(req.getPageInfo().getPageIndex(),req.getPageInfo().getPageSize());
        pageInfo.setRecordCount(businessMapper.superProduceCount(req));
        pageList.setPageInfo(pageInfo);
        req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1));
        req.setLimitEnd(pageInfo.getPageSize());
        List<TvSuperProduce> tvSuperProduces = businessMapper.superProduce(req);
        tvSuperProduces.stream().forEach(
                item -> {
                    Boolean fav = businessMapper.getFav(item.getSecurityId(), req.getLoginUserId());
                    if (fav != null) {
                        item.setFav(fav);
                    }
                }
        );
        pageList.setList(tvSuperProduces);
        return pageList;
    }

    @Override
    public TvPageList<TvCompanyRanking> companyRanking(TqCompanyRanking req) {
        if(req.getPageInfo() == null){
            req.setPageInfo(new PageInfo());
        }
        if (StringUtil.isNotEmpty(req.getKey())) {
            req.setKey("%"+req.getKey()+"%");
        }
        if (StringUtil.isNotEmpty(req.getRegCityStr())) {
            req.setRegCity(businessMapper.getRegCityCd("%"+req.getRegCityStr()+"%"));
        }
        if (StringUtil.isNotEmpty(req.getRegProvinceStr())) {
            req.setRegProvince(businessMapper.getRegCityCd("%"+req.getRegProvinceStr()+"%"));
        }
        TvPageList<TvCompanyRanking> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(req.getPageInfo().getPageIndex(),req.getPageInfo().getPageSize());
        pageInfo.setRecordCount(businessMapper.companyRankingCount(req));
        pageList.setPageInfo(pageInfo);
        req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1));
        req.setLimitEnd(pageInfo.getPageSize());
        //设置默认排序
        if (StringUtil.isEmpty(req.getPageInfo().getSortField())) {
            req.getPageInfo().setSortField(BusinessEnumContainer.CompanyRankingOrderBy.returnRate.getValue());
            req.getPageInfo().setSortDistanct("DESC");
        }
        req.setOrderBy(BusinessEnumContainer.CompanyRankingOrderBy.getDesc(req.getPageInfo().getSortField(),
                BusinessEnumContainer.CompanyRankingSuffix.getDesc(req.getSearchInterval())) + " " + req.getPageInfo().getSortDistanct());
        List<TvCompanyRanking> tvRankings = businessMapper.companyRanking(req);
        pageList.setList(tvRankings);
        return pageList;
    }


    @Override
    public TvPageList<TvManagerRanking> managerRanking(TqManagerRanking req) {
        if(req.getPageInfo() == null){
            req.setPageInfo(new PageInfo());
        }
        if (StringUtil.isNotEmpty(req.getKey())) {
            req.setKey("%"+req.getKey()+"%");
        }
        if (StringUtil.isNotEmpty(req.getRegCityStr())) {
            req.setRegCity(businessMapper.getRegCityCd("%"+req.getRegCityStr()+"%"));
        }
        if (StringUtil.isNotEmpty(req.getRegProvinceStr())) {
            req.setRegProvince(businessMapper.getRegCityCd("%"+req.getRegProvinceStr()+"%"));
        }
        TvPageList<TvManagerRanking> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(req.getPageInfo().getPageIndex(),req.getPageInfo().getPageSize());
        pageInfo.setRecordCount(businessMapper.managerRankingCount(req));
        pageList.setPageInfo(pageInfo);
        req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1));
        req.setLimitEnd(pageInfo.getPageSize());
        //设置默认排序
        if (StringUtil.isEmpty(req.getPageInfo().getSortField())) {
            req.getPageInfo().setSortField(BusinessEnumContainer.CompanyRankingOrderBy.returnRate.getValue());
            req.getPageInfo().setSortDistanct("DESC");
        }
        req.setOrderBy(BusinessEnumContainer.CompanyRankingOrderBy.getDesc(req.getPageInfo().getSortField(),
                BusinessEnumContainer.CompanyRankingSuffix.getDesc(req.getSearchInterval())) + " " + req.getPageInfo().getSortDistanct());
        List<TvManagerRanking> tvRankings = businessMapper.managerRanking(req);
        tvRankings.stream().forEach(
                item -> {
                    item.setPartyList(mdInstitutionService.getListByPersonId(item.getPersonId()));
                }
        );
        pageList.setList(tvRankings);
        return pageList;
    }

    @Override
    public BusinessEnumContainer.FundType getFundType(Integer securityId) {
        if(businessMapper.countPfundBySecurityId(securityId) > 0) {
            return BusinessEnumContainer.FundType.私募;
        }
        return BusinessEnumContainer.FundType.公募;
    }

    @Override
    public List<TvPfundMktList> pfundMktList() {
        return businessMapper.pfundMktList();
    }

    @Override
    public List<TvPfundMktHisNav> pfundMktHis(Date startDate, Date endDate, Integer securityId) {
        return businessMapper.pfundMktHis(startDate, endDate, securityId);
    }
}

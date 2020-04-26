package com.rong.tong.fund.service.impl;

import com.rong.common.consts.BusinessEnumContainer;
import com.rong.common.module.TvPageList;
import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.common.util.StringUtil;
import com.rong.tong.fund.mapper.FundClassMapper;
import com.rong.tong.fund.module.entity.TbFundClass;
import com.rong.tong.fund.module.request.TqFundClass;
import com.rong.tong.fund.module.view.TvFundClass;
import com.rong.tong.fund.service.FundClassService;
import com.rong.tong.pfunds.module.view.*;
import com.rong.tong.pfunds.service.impl.MdPeopleServiceImpl;
import com.vitily.mybatis.core.wrapper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FundClassServiceImpl extends FundsBasicServiceImpl<TbFundClass, TqFundClass, TvFundClass, FundClassMapper> implements FundClassService {


    @Autowired
    private MdPeopleServiceImpl mdPeopleService;

    @Override
    public int count(String key) {
        return mapper.count(key);
    }

    @Override
    public TvPageList<TvSearchPfundInfo> getSearchFundClassInfo(PageInfo pageInfo, String key, Long userId) {
        TvPageList<TvSearchPfundInfo> pageList = new TvPageList<>();
        pageInfo.setRecordCount(mapper.count(key));
        pageList.setPageInfo(pageInfo);
        String orderBy = null;
        if (StringUtil.isNotEmpty(pageInfo.getSortField())) {
            orderBy = BusinessEnumContainer.SearchFundClassOrderBy.getDesc(pageInfo.getSortField()) + " " + pageInfo.getSortDistanct();
        }
        List<TvSearchPfundInfo> tvSearchPfundInfos = mapper.selectSearchFundClassInfoList(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1), pageInfo.getPageSize(), key, orderBy, userId);
        tvSearchPfundInfos.stream().forEach(
                item -> {
                    item.setManagers(mdPeopleService.getListByFundSecurityId(item.getSecurityId()));
                }
        );
        pageList.setList(tvSearchPfundInfos);
        return pageList;
    }

    @Override
    public TvProduceSummary getFundInfoSummary(Integer securityId, Long userId) {
        TvProduceSummary fundInfoSummary = mapper.getFundInfoSummary(securityId, userId);
        fundInfoSummary.setManagers(mdPeopleService.getListByFundSecurityId(securityId));
        return fundInfoSummary;
    }

    @Override
    public TvPageList<TvHisNav> hisNav(PageInfo page, Integer securityId) {
        TvPageList<TvHisNav> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(page.getPageIndex(),page.getPageSize());
        pageInfo.setRecordCount(mapper.hisNavCount(securityId));
        pageList.setPageInfo(pageInfo);
        pageList.setList(mapper.hisNav(pageInfo.getPageSize() * (pageInfo.getPageIndex()-1),pageInfo.getPageSize(), securityId));
        return pageList;
    }

    @Override
    public TvDateBoundary hisNavDateBoundary(Integer securityId) {
        return mapper.hisNavDateBoundary(securityId);
    }

    @Override
    public List<TvHisNav> accumNavTrend(Date startDate, Date endDate, Integer securityId) {
        return mapper.accumNavTrend(startDate, endDate, securityId);
    }

    @Override
    public  List<TvMaxDrawdownTrend> maxDrawdownTrend(Integer securityId) {
        return mapper.maxDrawdownTrend(securityId);
    }

    @Override
    public  TvMaxDrawdownTrend maxHisDrawdown(Integer securityId) {
        return mapper.maxHisDrawdown(securityId);
    }

    @Override
    public TvIntervalReturn getIntervalReturn(Integer securityId) {
        return mapper.getIntervalReturn(securityId);
    }

    @Override
    public List<TvYearMonReturn> getYearReturn(Integer securityId) {
        return mapper.getYearReturn(securityId);
    }

    @Override
    public List<TvYearMonReturn> getMonReturn(Integer securityId) {
        return mapper.getMonReturn(securityId);
    }

    @Override
    public List<TvRiskAssessment> riskAssessment(Integer securityId) {
        List<TvRiskAssessment> tvRiskAssessments = mapper.riskAssessment(securityId);
        tvRiskAssessments.stream().forEach(
                item -> {
                    item.setWindow(BusinessEnumContainer.WindowType.getDesc(Integer.valueOf(item.getWindow())));
                }
        );
        return tvRiskAssessments;
    }

    @Override
    public List<TvFundStyle> fundStyle(Integer securityId) {
        return mapper.fundStyle(securityId);
    }

    @Override
    public TvProduceInformation produceInformation(Integer securityId) {
        return mapper.produceInformation(securityId);
    }
}
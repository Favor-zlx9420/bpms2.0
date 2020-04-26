package com.rong.fundmanage.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.DateUtil;
import com.rong.fundmanage.mapper.PrivateFundsCurrentGrMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentGr;
import com.rong.fundmanage.module.request.TqPrivateFundsCurrentGr;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentGr;
import com.rong.fundmanage.service.PrivateFundsCurrentGrService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PrivateFundsCurrentGrServiceImpl extends BasicServiceImpl<TbPrivateFundsCurrentGr, TqPrivateFundsCurrentGr, TvPrivateFundsCurrentGr, PrivateFundsCurrentGrMapper> implements PrivateFundsCurrentGrService {
    @Override
    public void resetGr() {
        mapper.resetGrTemp();
        if (mapper.resetGrTempCount() > 0) {
            mapper.resetGr();
        }
    }

    @Override
    public void resetYear() {
        int nowYear = DateUtil.getYear(new Date());
        String y1 = String.valueOf(nowYear - 1);
        String y2 = String.valueOf(nowYear - 2);
        String y3 = String.valueOf(nowYear - 3);
        String y4 = String.valueOf(nowYear - 4);
        String y5 = String.valueOf(nowYear - 5);
        String y6 = String.valueOf(nowYear - 6);
        String y7 = String.valueOf(nowYear - 7);
        String y8 = String.valueOf(nowYear - 8);
        String nowY = String.valueOf(nowYear);
        mapper.resetYearTemp(y1, y2, y3, y4, y5, y6, y7, y8, nowY);
        if (mapper.resetYearTempCount() > 0) {
            mapper.resetYear();
        }
    }

    @Override
    public void restCompanyRanking() {
        mapper.restCompanyRankingTemp();
        if (mapper.restCompanyRankingTempCount() > 0) {
            mapper.restCompanyRanking();
        }
    }

    @Override
    public void restManagerRanking() {
        mapper.restManagerRankingTemp();
        if (mapper.restManagerRankingTempCount() > 0) {
            mapper.restManagerRanking();
        }
    }

    @Override
    public void restPfundRanking() {
        mapper.restPfundRankingTemp();
        if (mapper.restPfundRankingTempCount() > 0) {
            mapper.restPfundRanking();
        }
    }

    @Override
    public void resetPrivatePerf() {
        mapper.resetPrivatePerfTemp();
        if (mapper.resetPrivatePerfTempCount() > 0) {
            mapper.resetPrivatePerf();
        }
    }

    @Override
    public void deleteNewsSummary() {
        mapper.deleteNewsSummary();
    }
}
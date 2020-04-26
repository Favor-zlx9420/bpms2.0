package com.rong.fundmanage.service;

import com.rong.common.service.BasicService;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentGr;
import com.rong.fundmanage.module.request.TqPrivateFundsCurrentGr;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentGr;

public interface PrivateFundsCurrentGrService extends BasicService<TbPrivateFundsCurrentGr, TqPrivateFundsCurrentGr, TvPrivateFundsCurrentGr> {
    void resetGr();

    void resetYear();

    void restCompanyRanking();

    void restManagerRanking();

    void restPfundRanking();

    void resetPrivatePerf();

    void deleteNewsSummary();
}
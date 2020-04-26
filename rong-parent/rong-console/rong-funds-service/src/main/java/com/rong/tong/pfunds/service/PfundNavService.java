package com.rong.tong.pfunds.service;

import com.rong.common.service.FundsBasicService;
import com.rong.tong.pfunds.module.entity.TbPfundNav;
import com.rong.tong.pfunds.module.request.TqPfundNav;
import com.rong.tong.pfunds.module.view.TvHisNav;
import com.rong.tong.pfunds.module.view.TvPfundNav;

import java.util.Date;
import java.util.List;

public interface PfundNavService extends FundsBasicService<TbPfundNav, TqPfundNav, TvPfundNav> {
    List<TvHisNav> accumNavTrendOfManager(Long personId, Date startDate, Date endDate);
    List<TvHisNav> accumNavTrendOfOrg(Long partyId, Date startDate, Date endDate);
}
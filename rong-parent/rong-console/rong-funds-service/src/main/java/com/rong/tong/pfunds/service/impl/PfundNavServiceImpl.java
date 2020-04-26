package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.PfundNavMapper;
import com.rong.tong.pfunds.module.entity.TbPfundNav;
import com.rong.tong.pfunds.module.request.TqPfundNav;
import com.rong.tong.pfunds.module.view.TvHisNav;
import com.rong.tong.pfunds.module.view.TvPfundNav;
import com.rong.tong.pfunds.service.PfundNavService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PfundNavServiceImpl extends FundsBasicServiceImpl<TbPfundNav, TqPfundNav, TvPfundNav, PfundNavMapper> implements PfundNavService {
    @Override
    public List<TvHisNav> accumNavTrendOfManager(Long personId, Date startDate, Date endDate) {
        return mapper.accumNavTrendOfManager(personId,startDate,endDate);
    }

    @Override
    public List<TvHisNav> accumNavTrendOfOrg(Long partyId, Date startDate, Date endDate) {
        return mapper.accumNavTrendOfOrg(partyId,startDate,endDate);
    }
}
package com.rong.tong.pfunds.service;

import com.rong.common.service.FundsBasicService;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.request.TqMdPeople;
import com.rong.tong.pfunds.module.view.TvMdPeople;

import java.util.List;

public interface MdPeopleService extends FundsBasicService<TbMdPeople, TqMdPeople, TvMdPeople> {

    List<TvMdPeople> getListByPfundSecurityId(Integer securityId);

    List<TvMdPeople> getListByFundSecurityId(Integer securityId);
}
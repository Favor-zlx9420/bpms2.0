package com.rong.tong.pfunds.service.impl;

import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.tong.pfunds.mapper.MdPeopleMapper;
import com.rong.tong.pfunds.module.entity.TbMdPeople;
import com.rong.tong.pfunds.module.request.TqMdPeople;
import com.rong.tong.pfunds.module.view.TvMdPeople;
import com.rong.tong.pfunds.service.MdPeopleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MdPeopleServiceImpl extends FundsBasicServiceImpl<TbMdPeople, TqMdPeople, TvMdPeople, MdPeopleMapper> implements MdPeopleService {
    @Override
    public List<TvMdPeople> getListByPfundSecurityId(Integer securityId) {
        return mapper.getListByPfundSecurityId(securityId);
    }

    @Override
    public List<TvMdPeople> getListByFundSecurityId(Integer securityId) {
        return mapper.getListByFundSecurityId(securityId);
    }
}
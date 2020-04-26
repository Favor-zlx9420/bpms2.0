package com.rong.fundmanage.service.impl;

import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.fundmanage.mapper.PrivateFundsCurrentIncomeMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentIncome;
import com.rong.fundmanage.module.request.TqPrivateFundsCurrentIncome;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentIncome;
import com.rong.fundmanage.service.PrivateFundsCurrentIncomeService;
import org.springframework.stereotype.Service;

@Service
public class PrivateFundsCurrentIncomeServiceImpl extends BasicServiceImpl<TbPrivateFundsCurrentIncome, TqPrivateFundsCurrentIncome, TvPrivateFundsCurrentIncome, PrivateFundsCurrentIncomeMapper> implements PrivateFundsCurrentIncomeService {
    @Override
    public void resetIncome() {
        mapper.resetIncomeTemp();
        if (mapper.resetIncomeTempCount() > 0) {
            mapper.resetIncome();
        }
    }
}
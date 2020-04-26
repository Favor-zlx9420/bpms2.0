package com.rong.fundmanage.service;

import com.rong.common.service.BasicService;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentIncome;
import com.rong.fundmanage.module.request.TqPrivateFundsCurrentIncome;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentIncome;

public interface PrivateFundsCurrentIncomeService extends BasicService<TbPrivateFundsCurrentIncome, TqPrivateFundsCurrentIncome, TvPrivateFundsCurrentIncome> {
    void resetIncome();
}
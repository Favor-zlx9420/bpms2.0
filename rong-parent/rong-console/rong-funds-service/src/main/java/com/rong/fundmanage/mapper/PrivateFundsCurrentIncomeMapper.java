package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentIncome;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentIncome;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PrivateFundsCurrentIncomeMapper extends CommonBasicMapper<TbPrivateFundsCurrentIncome, TvPrivateFundsCurrentIncome>, MultiTableMapper<TbPrivateFundsCurrentIncome, TvPrivateFundsCurrentIncome> {
    void resetIncomeTemp();

    Integer resetIncomeTempCount();

    void resetIncome();
}
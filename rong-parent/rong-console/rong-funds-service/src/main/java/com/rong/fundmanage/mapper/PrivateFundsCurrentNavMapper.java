package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbPrivateFundsCurrentNav;
import com.rong.fundmanage.module.view.TvPrivateFundsCurrentNav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PrivateFundsCurrentNavMapper extends CommonBasicMapper<TbPrivateFundsCurrentNav, TvPrivateFundsCurrentNav>, MultiTableMapper<TbPrivateFundsCurrentNav, TvPrivateFundsCurrentNav> {
    void resetNavTemp();

    Integer resetNavTempCount();

    void resetNav();
}
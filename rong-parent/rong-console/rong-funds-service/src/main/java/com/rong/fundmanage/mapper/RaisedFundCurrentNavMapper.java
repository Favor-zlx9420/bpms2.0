package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbRaisedFundCurrentNav;
import com.rong.fundmanage.module.view.TvRaisedFundCurrentNav;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface RaisedFundCurrentNavMapper extends CommonBasicMapper<TbRaisedFundCurrentNav, TvRaisedFundCurrentNav>, MultiTableMapper<TbRaisedFundCurrentNav, TvRaisedFundCurrentNav> {
    void resetNavTemp();

    Integer resetNavTempCount();

    void resetNav();
}
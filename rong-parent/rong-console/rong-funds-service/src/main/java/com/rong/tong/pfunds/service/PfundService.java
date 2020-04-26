package com.rong.tong.pfunds.service;

import com.rong.common.module.TvPageList;
import com.rong.common.service.FundsBasicService;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.request.TqPfund;
import com.rong.tong.pfunds.module.view.TvPfund;
import com.rong.tong.pfunds.module.view.TvSearchPfundInfo;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;

public interface PfundService extends FundsBasicService<TbPfund, TqPfund, TvPfund> {
    TvPageList<TvSearchPfundInfo> getSearchPfundInfo(PageInfo pageInfo, String key, Long userId);

    Integer count(String key);

    TvSearchPfundInfo getNavDetail(Integer securityId);
}
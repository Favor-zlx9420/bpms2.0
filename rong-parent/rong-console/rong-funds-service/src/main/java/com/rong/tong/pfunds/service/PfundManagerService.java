package com.rong.tong.pfunds.service;

import com.rong.common.module.TvPageList;
import com.rong.common.service.FundsBasicService;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
import com.rong.tong.pfunds.module.request.TqPfundManager;
import com.rong.tong.pfunds.module.view.TvPfundManager;
import com.rong.tong.pfunds.module.view.TvSearchPfundManager;
import com.vitily.mybatis.core.wrapper.PageInfo;

public interface PfundManagerService extends FundsBasicService<TbPfundManager, TqPfundManager, TvPfundManager> {
    TvPageList<TvSearchPfundManager> getSearchPfundManager(PageInfo pageInfo, String key);

    Integer count(String key);
}
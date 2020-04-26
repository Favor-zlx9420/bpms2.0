package com.rong.tong.pfunds.service;

import com.rong.common.module.TvPageList;
import com.rong.common.service.FundsBasicService;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import com.rong.tong.pfunds.module.request.TqPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvSearchPfundInstInfo;
import com.vitily.mybatis.core.wrapper.PageInfo;

public interface PfundInstInfoService extends FundsBasicService<TbPfundInstInfo, TqPfundInstInfo, TvPfundInstInfo> {
    TvPageList<TvSearchPfundInstInfo> getSearchPfundInstInfo(PageInfo pageInfo, String s);

    Integer count(String key);
}
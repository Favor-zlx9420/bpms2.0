package com.rong.tong.pfunds.service.impl;

import com.rong.common.consts.BusinessEnumContainer;
import com.rong.common.module.TvPageList;
import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.common.util.StringUtil;
import com.rong.tong.pfunds.mapper.PfundInstInfoMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import com.rong.tong.pfunds.module.request.TqPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvSearchPfundInstInfo;
import com.rong.tong.pfunds.service.PfundInstInfoService;
import com.vitily.mybatis.core.wrapper.Page;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PfundInstInfoServiceImpl extends FundsBasicServiceImpl<TbPfundInstInfo, TqPfundInstInfo, TvPfundInstInfo, PfundInstInfoMapper> implements PfundInstInfoService {
    @Override
    public TvPageList<TvSearchPfundInstInfo> getSearchPfundInstInfo(PageInfo pageInfo, String key){
        TvPageList<TvSearchPfundInstInfo> pageList = new TvPageList<>();
        pageInfo.setRecordCount(mapper.count(key));
        pageList.setPageInfo(pageInfo);
        String orderBy = null;
        if (StringUtil.isNotEmpty(pageInfo.getSortField())) {
            orderBy = BusinessEnumContainer.SearchPfundInstOrderBy.getDesc(pageInfo.getSortField()) + " " + pageInfo.getSortDistanct();
        }
        List<TvSearchPfundInstInfo> tvSearchPfundInstInfos = mapper.selectSearchPfundInstInfoList(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1), pageInfo.getPageSize(), key, orderBy);
        pageList.setList(tvSearchPfundInstInfos);
        return pageList;
    }

    @Override
    public Integer count(String key) {
        return mapper.count(key);
    }
}
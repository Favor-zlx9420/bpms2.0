package com.rong.tong.pfunds.service.impl;

import com.rong.common.consts.BusinessEnumContainer;
import com.rong.common.module.TvPageList;
import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.common.util.StringUtil;
import com.rong.tong.pfunds.mapper.PfundMapper;
import com.rong.tong.pfunds.module.entity.TbPfund;
import com.rong.tong.pfunds.module.request.TqPfund;
import com.rong.tong.pfunds.module.view.TvPfund;
import com.rong.tong.pfunds.module.view.TvSearchPfundInfo;
import com.rong.tong.pfunds.service.PfundService;
import com.vitily.mybatis.core.wrapper.Page;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PfundServiceImpl extends FundsBasicServiceImpl<TbPfund, TqPfund, TvPfund, PfundMapper> implements PfundService {

    @Autowired
    private MdPeopleServiceImpl mdPeopleService;

    @Override
    public TvPageList<TvSearchPfundInfo> getSearchPfundInfo(PageInfo pageInfo, String key, Long userId) {
        TvPageList<TvSearchPfundInfo> pageList = new TvPageList<>();
        pageInfo.setRecordCount(mapper.count(key));
        pageList.setPageInfo(pageInfo);
        String orderBy = null;
        if (StringUtil.isNotEmpty(pageInfo.getSortField())) {
            orderBy = BusinessEnumContainer.SearchPfundOrderBy.getDesc(pageInfo.getSortField()) + " " + pageInfo.getSortDistanct();
        }
        List<TvSearchPfundInfo> tvSearchPfundInfos = mapper.selectSearchPfundInfoList(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1), pageInfo.getPageSize(), key, orderBy, userId);
        tvSearchPfundInfos.stream().forEach(
                item -> {
                    item.setManagers(mdPeopleService.getListByPfundSecurityId(item.getSecurityId()));
                }
        );

        pageList.setList(tvSearchPfundInfos);
        return pageList;
    }

    @Override
    public Integer count(String key) {
        return mapper.count(key);
    }

    @Override
    public TvSearchPfundInfo getNavDetail(Integer securityId) {
        return mapper.getNavDetail(securityId);
    }
}
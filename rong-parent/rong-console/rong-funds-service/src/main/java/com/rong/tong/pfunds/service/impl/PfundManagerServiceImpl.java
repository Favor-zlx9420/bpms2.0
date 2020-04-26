package com.rong.tong.pfunds.service.impl;

import com.rong.common.consts.BusinessEnumContainer;
import com.rong.common.module.TvPageList;
import com.rong.common.service.impl.FundsBasicServiceImpl;
import com.rong.common.util.StringUtil;
import com.rong.tong.pfunds.mapper.PfundManagerMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstInfo;
import com.rong.tong.pfunds.module.entity.TbPfundManager;
import com.rong.tong.pfunds.module.request.TqPfundManager;
import com.rong.tong.pfunds.module.view.TvPfundManager;
import com.rong.tong.pfunds.module.view.TvSearchPfundInstInfo;
import com.rong.tong.pfunds.module.view.TvSearchPfundManager;
import com.rong.tong.pfunds.service.MdInstitutionService;
import com.rong.tong.pfunds.service.PfundManagerService;
import com.vitily.mybatis.core.wrapper.Page;
import com.vitily.mybatis.core.wrapper.PageInfo;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PfundManagerServiceImpl extends FundsBasicServiceImpl<TbPfundManager, TqPfundManager, TvPfundManager, PfundManagerMapper> implements PfundManagerService {

    @Autowired
    private MdInstitutionService mdInstitutionService;

    @Override
    public TvPageList<TvSearchPfundManager> getSearchPfundManager(PageInfo pageInfo, String key){
        TvPageList<TvSearchPfundManager> pageList = new TvPageList<>();
        pageInfo.setRecordCount(mapper.count(key));
        pageList.setPageInfo(pageInfo);
        String orderBy = null;
        if (StringUtil.isNotEmpty(pageInfo.getSortField())) {
            orderBy = BusinessEnumContainer.SearchPfundManagerOrderBy.getDesc(pageInfo.getSortField()) + " " + pageInfo.getSortDistanct();
        }
        List<TvSearchPfundManager> tvSearchPfundManagerss = mapper.selectSearchPfundManagerList(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1), pageInfo.getPageSize(), key, orderBy);
        tvSearchPfundManagerss.stream().forEach(
                item -> {
                    item.setPartyList(mdInstitutionService.getListByPersonId(item.getPersonId()));
                }
        );
        pageList.setList(tvSearchPfundManagerss);
        return pageList;
    }

    @Override
    public Integer count(String key) {
        return mapper.count(key);
    }
}
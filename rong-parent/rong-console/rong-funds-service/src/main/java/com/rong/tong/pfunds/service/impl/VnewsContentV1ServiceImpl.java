package com.rong.tong.pfunds.service.impl;

import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.module.TvPageList;
import com.rong.common.service.impl.BasicServiceImpl;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.tong.pfunds.mapper.VnewsContentV1Mapper;
import com.rong.tong.pfunds.module.entity.TbVnewsContentV1;
import com.rong.tong.pfunds.module.request.TqNewsDetail;
import com.rong.tong.pfunds.module.request.TqNewsList;
import com.rong.tong.pfunds.module.request.TqVnewsContentV1;
import com.rong.tong.pfunds.module.view.TvNewsCate;
import com.rong.tong.pfunds.module.view.TvNewsDetail;
import com.rong.tong.pfunds.module.view.TvNewsList;
import com.rong.tong.pfunds.module.view.TvVnewsContentV1;
import com.rong.tong.pfunds.service.VnewsContentV1Service;
import com.vitily.mybatis.core.wrapper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VnewsContentV1ServiceImpl extends BasicServiceImpl<TbVnewsContentV1, TqVnewsContentV1, TvVnewsContentV1, VnewsContentV1Mapper> implements VnewsContentV1Service {

    @Autowired
    DictionaryCache dictionaryCache;

    @Override
    public TvPageList<TvNewsList> pageList(TqNewsList req) {
        if(req.getPageInfo() == null){
            req.setPageInfo(new PageInfo());
        }
        Integer hotCateId = Integer.valueOf(dictionaryCache.getValue(DictionaryKey.Keys.HOT_NEWS_COLUMN.getValue()));
        TvPageList<TvNewsList> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(req.getPageInfo().getPageIndex(),req.getPageInfo().getPageSize());
        if (req.getCateId() == hotCateId) {
            TqNewsList tempTqNewsList = new TqNewsList();
            tempTqNewsList.setCateId(hotCateId);
            tempTqNewsList.setLimitStart(0);
            tempTqNewsList.setLimitEnd(3);
            List<TvNewsList> hotNewsLists = mapper.pageListSelf(tempTqNewsList);
            int day = Integer.valueOf(dictionaryCache.getValue(DictionaryKey.Keys.ACCESS_INFORMATION_RELEASE_DATE_LIMITS_THE_NUMBER_OF_RECENT_DAYS.getValue()));
            if (day != 0) {
                req.setStartDate(DateUtil.addDate(new Date(),-1*day));
            }
            pageInfo.setRecordCount(mapper.count(req)+hotNewsLists.size());
            pageList.setPageInfo(pageInfo);
            //热点资讯第一页要特殊处理
            if (req.getPageInfo().getPageIndex() == 1) {
                req.setLimitStart(0);
                req.setLimitEnd(pageInfo.getPageSize() - hotNewsLists.size());
                List<TvNewsList> tvNewsLists = mapper.pageList(req);
                hotNewsLists.addAll(tvNewsLists);
                pageList.setList(hotNewsLists);
                return pageList;
            }
            req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1) - hotNewsLists.size());
            req.setLimitEnd(pageInfo.getPageSize());
            List<TvNewsList> tvNewsLists = mapper.pageList(req);
            pageList.setList(tvNewsLists);
            return pageList;
        }
        pageInfo.setRecordCount(mapper.pageListSelfCount(req));
        pageList.setPageInfo(pageInfo);
        req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1));
        req.setLimitEnd(pageInfo.getPageSize());
        List<TvNewsList> tvNewsLists = mapper.pageListSelf(req);
        pageList.setList(tvNewsLists);
        return pageList;
    }

    @Override
    public TvNewsDetail detail(TqNewsDetail req) {
        TvNewsDetail detail = mapper.detail(req);
        if (detail != null) {
            detail.setS3Url(dictionaryCache.getValue(DictionaryKey.Keys.NEWS_CONTENT_ACCESS_PREFIX.getValue()) + detail.getS3Url());
        }
        return detail;
    }

    @Override
    public List<TvNewsList> hostSearch() {
        return mapper.hotSearch();
    }


    @Override
    public TvNewsDetail detailSelf(TqNewsDetail req) {
        return mapper.detailSelf(req);
    }

    @Override
    public List<TvNewsCate> newsCate() {
        return mapper.newsCate();
    }

    @Override
    public TvPageList<TvNewsList> search(TqNewsList req) {
        if(req.getPageInfo() == null){
            req.setPageInfo(new PageInfo());
        }
        if (StringUtil.isNotEmpty(req.getKey())){
            req.setKey("%"+req.getKey()+"%");
        }
        TvPageList<TvNewsList> pageList = new TvPageList<>();
        PageInfo pageInfo = PageInfo.valueOf(req.getPageInfo().getPageIndex(),req.getPageInfo().getPageSize());
        int selfCount = mapper.pageListSelfCount(req);
        pageInfo.setRecordCount(selfCount + mapper.count(req));
        pageList.setPageInfo(pageInfo);
        //只查自产库
        if (pageInfo.getPageSize() * (pageInfo.getPageIndex()) <= selfCount) {
            req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1));
            req.setLimitEnd(pageInfo.getPageSize());
            pageList.setList(mapper.pageListSelf(req));
            return pageList;
        }
        //只查通联库
        if (pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1) > selfCount) {
            req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1) - selfCount);
            req.setLimitEnd(pageInfo.getPageSize());
            pageList.setList(mapper.pageList(req));
            return pageList;
        }
        req.setLimitStart(pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1));
        req.setLimitEnd(selfCount - (pageInfo.getPageSize() * (pageInfo.getPageIndex() - 1)));
        List<TvNewsList> hotNewsLists = mapper.pageListSelf(req);
        req.setLimitStart(0);
        req.setLimitEnd(pageInfo.getPageSize() - hotNewsLists.size());
        hotNewsLists.addAll(mapper.pageList(req));
        pageList.setList(hotNewsLists);
        return pageList;
    }
}
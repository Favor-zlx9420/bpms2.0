package com.rong.tong.pfunds.service;

import com.rong.common.module.TvPageList;
import com.rong.common.service.FundsBasicService;
import com.rong.tong.pfunds.module.entity.TbVnewsContentV1;
import com.rong.tong.pfunds.module.request.TqNewsDetail;
import com.rong.tong.pfunds.module.request.TqNewsList;
import com.rong.tong.pfunds.module.request.TqVnewsContentV1;
import com.rong.tong.pfunds.module.view.TvNewsCate;
import com.rong.tong.pfunds.module.view.TvNewsDetail;
import com.rong.tong.pfunds.module.view.TvNewsList;
import com.rong.tong.pfunds.module.view.TvVnewsContentV1;

import java.util.List;

public interface VnewsContentV1Service extends FundsBasicService<TbVnewsContentV1, TqVnewsContentV1, TvVnewsContentV1> {
    TvPageList<TvNewsList> pageList(TqNewsList req);

    TvNewsDetail detail(TqNewsDetail req);

    List<TvNewsList> hostSearch();

    TvNewsDetail detailSelf(TqNewsDetail req);

    List<TvNewsCate> newsCate();

    TvPageList<TvNewsList> search(TqNewsList req);
}
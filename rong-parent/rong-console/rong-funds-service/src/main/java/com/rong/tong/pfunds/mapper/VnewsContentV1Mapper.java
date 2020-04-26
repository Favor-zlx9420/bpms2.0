package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbVnewsContentV1;
import com.rong.tong.pfunds.module.request.TqNewsDetail;
import com.rong.tong.pfunds.module.request.TqNewsList;
import com.rong.tong.pfunds.module.view.*;
import com.vitily.mybatis.core.mapper.MultiTableMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VnewsContentV1Mapper extends CommonBasicMapper<TbVnewsContentV1, TvVnewsContentV1>, MultiTableMapper<TbVnewsContentV1, TvVnewsContentV1> {
    int count(TqNewsList req);

    List<TvNewsList> pageList(TqNewsList req);

    TvNewsDetail detail(TqNewsDetail req);

    List<TvNewsList> hotSearch();

    TvNewsDetail detailSelf(TqNewsDetail req);

    int pageListSelfCount(TqNewsList req);

    List<TvNewsList> pageListSelf(TqNewsList req);

    List<TvNewsCate> newsCate();
}
package com.rong.cms.mapper;

import com.rong.cms.module.entity.TbCmsNews;
import com.rong.cms.module.view.TvCmsNews;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

import java.util.List;

public interface CmsNewsMapper extends CommonBasicMapper<TbCmsNews, TvCmsNews>, MultiTableMapper<TbCmsNews, TvCmsNews> {
    List<TbCmsNews> selectCmsNewsInfoList();
}
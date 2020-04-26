package com.rong.cms.service;

import com.rong.cms.module.entity.TbCmsNews;
import com.rong.cms.module.request.TqCmsNews;
import com.rong.cms.module.view.TvCmsNews;
import com.rong.common.service.BasicService;

import java.util.List;

public interface CmsNewsService extends BasicService<TbCmsNews, TqCmsNews, TvCmsNews> {

    List<TbCmsNews> selectCmsNewsInfoList();
}
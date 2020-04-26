package com.rong.cms.mapper;

import com.rong.cms.module.entity.TbCmsCategory;
import com.rong.cms.module.view.TvCmsCategory;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface CmsCategoryMapper extends CommonBasicMapper<TbCmsCategory, TvCmsCategory>, MultiTableMapper<TbCmsCategory, TvCmsCategory> {
}
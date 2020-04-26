package com.rong.cms.mapper;

import com.rong.cms.module.entity.TbCmsType;
import com.rong.cms.module.view.TvCmsType;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface CmsTypeMapper extends CommonBasicMapper<TbCmsType, TvCmsType>, MultiTableMapper<TbCmsType, TvCmsType> {
}
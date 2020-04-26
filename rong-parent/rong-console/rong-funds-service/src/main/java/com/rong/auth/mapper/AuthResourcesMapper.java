package com.rong.auth.mapper;

import com.rong.auth.module.entity.TbAuthResources;
import com.rong.auth.module.view.TvAuthResources;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AuthResourcesMapper extends CommonBasicMapper<TbAuthResources, TvAuthResources>, MultiTableMapper<TbAuthResources, TvAuthResources> {
}
package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbOrgProxy;
import com.rong.fundmanage.module.view.TvOrgProxy;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface OrgProxyMapper extends CommonBasicMapper<TbOrgProxy, TvOrgProxy>, MultiTableMapper<TbOrgProxy, TvOrgProxy> {
}
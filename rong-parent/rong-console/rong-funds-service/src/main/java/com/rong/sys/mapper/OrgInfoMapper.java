package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbOrgInfo;
import com.rong.sys.module.view.TvOrgInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface OrgInfoMapper extends CommonBasicMapper<TbOrgInfo, TvOrgInfo>, MultiTableMapper<TbOrgInfo, TvOrgInfo> {
}
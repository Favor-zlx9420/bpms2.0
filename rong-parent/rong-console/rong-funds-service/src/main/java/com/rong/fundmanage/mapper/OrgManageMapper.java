package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbOrgManage;
import com.rong.fundmanage.module.view.TvOrgManage;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface OrgManageMapper extends CommonBasicMapper<TbOrgManage, TvOrgManage>, MultiTableMapper<TbOrgManage, TvOrgManage> {
}
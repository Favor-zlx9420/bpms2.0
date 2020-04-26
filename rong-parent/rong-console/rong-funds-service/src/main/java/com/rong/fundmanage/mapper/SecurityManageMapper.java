package com.rong.fundmanage.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import com.rong.fundmanage.module.view.TvSecurityManage;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface SecurityManageMapper extends CommonBasicMapper<TbSecurityManage, TvSecurityManage>, MultiTableMapper<TbSecurityManage, TvSecurityManage> {
}
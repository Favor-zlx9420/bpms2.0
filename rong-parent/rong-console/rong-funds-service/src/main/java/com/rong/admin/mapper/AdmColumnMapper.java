package com.rong.admin.mapper;

import com.rong.admin.module.entity.TbAdmColumn;
import com.rong.admin.module.view.TvAdmColumn;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AdmColumnMapper extends CommonBasicMapper<TbAdmColumn, TvAdmColumn>, MultiTableMapper<TbAdmColumn, TvAdmColumn> {
}
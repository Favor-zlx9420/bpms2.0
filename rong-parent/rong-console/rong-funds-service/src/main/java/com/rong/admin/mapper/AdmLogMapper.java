package com.rong.admin.mapper;

import com.rong.admin.module.entity.TbAdmLog;
import com.rong.admin.module.view.TvAdmLog;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AdmLogMapper extends CommonBasicMapper<TbAdmLog, TvAdmLog>, MultiTableMapper<TbAdmLog, TvAdmLog> {
}
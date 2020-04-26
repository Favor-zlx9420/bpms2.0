package com.rong.admin.mapper;

import com.rong.admin.module.entity.TbAdmFields;
import com.rong.admin.module.view.TvAdmFields;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AdmFieldsMapper extends CommonBasicMapper<TbAdmFields, TvAdmFields>, MultiTableMapper<TbAdmFields, TvAdmFields> {
}
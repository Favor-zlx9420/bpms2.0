package com.rong.admin.mapper;

import com.rong.admin.module.entity.TbAdmMemorandum;
import com.rong.admin.module.view.TvAdmMemorandum;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface AdmMemorandumMapper extends CommonBasicMapper<TbAdmMemorandum, TvAdmMemorandum>, MultiTableMapper<TbAdmMemorandum, TvAdmMemorandum> {
}
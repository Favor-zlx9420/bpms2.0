package com.rong.admin.service;

import com.rong.admin.module.entity.TbAdmFields;
import com.rong.admin.module.request.TqAdmFields;
import com.rong.admin.module.view.TvAdmFields;
import com.rong.common.service.BasicService;

import java.util.List;

public interface AdmFieldsService extends BasicService<TbAdmFields, TqAdmFields, TvAdmFields> {
    List<TvAdmFields> getFieldShowByColumnId(long columnId);
}
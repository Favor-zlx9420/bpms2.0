package com.rong.cms.mapper;

import com.rong.cms.module.entity.TbCmsDynProper;
import com.rong.cms.module.view.TvCmsDynProper;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface CmsDynProperMapper extends CommonBasicMapper<TbCmsDynProper, TvCmsDynProper>, MultiTableMapper<TbCmsDynProper, TvCmsDynProper> {
}
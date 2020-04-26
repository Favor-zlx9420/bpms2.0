package com.rong.cms.mapper;

import com.rong.cms.module.entity.TbCmsDynproVal;
import com.rong.cms.module.view.TvCmsDynproVal;
import com.rong.common.mapper.CommonBasicMapper;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface CmsDynproValMapper extends CommonBasicMapper<TbCmsDynproVal, TvCmsDynproVal>, MultiTableMapper<TbCmsDynproVal, TvCmsDynproVal> {
}
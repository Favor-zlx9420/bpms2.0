package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundRel;
import com.rong.tong.pfunds.module.view.TvPfundRel;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundRelMapper extends CommonBasicMapper<TbPfundRel, TvPfundRel>, MultiTableMapper<TbPfundRel, TvPfundRel> {
}
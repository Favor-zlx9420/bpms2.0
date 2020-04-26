package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundPerfIndic;
import com.rong.tong.pfunds.module.view.TvPfundPerfIndic;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundPerfIndicMapper extends CommonBasicMapper<TbPfundPerfIndic, TvPfundPerfIndic>, MultiTableMapper<TbPfundPerfIndic, TvPfundPerfIndic> {
}
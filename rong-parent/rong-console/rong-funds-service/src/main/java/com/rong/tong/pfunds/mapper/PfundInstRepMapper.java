package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstRep;
import com.rong.tong.pfunds.module.view.TvPfundInstRep;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundInstRepMapper extends CommonBasicMapper<TbPfundInstRep, TvPfundInstRep>, MultiTableMapper<TbPfundInstRep, TvPfundInstRep> {
}
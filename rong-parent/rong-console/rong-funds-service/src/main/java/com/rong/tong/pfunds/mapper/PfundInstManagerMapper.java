package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstManager;
import com.rong.tong.pfunds.module.view.TvPfundInstManager;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundInstManagerMapper extends CommonBasicMapper<TbPfundInstManager, TvPfundInstManager>, MultiTableMapper<TbPfundInstManager, TvPfundInstManager> {
}
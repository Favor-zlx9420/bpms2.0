package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstAmac;
import com.rong.tong.pfunds.module.view.TvPfundInstAmac;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundInstAmacMapper extends CommonBasicMapper<TbPfundInstAmac, TvPfundInstAmac>, MultiTableMapper<TbPfundInstAmac, TvPfundInstAmac> {
}
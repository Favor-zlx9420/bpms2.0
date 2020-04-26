package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundInstScaleAmac;
import com.rong.tong.pfunds.module.view.TvPfundInstScaleAmac;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundInstScaleAmacMapper extends CommonBasicMapper<TbPfundInstScaleAmac, TvPfundInstScaleAmac>, MultiTableMapper<TbPfundInstScaleAmac, TvPfundInstScaleAmac> {
}
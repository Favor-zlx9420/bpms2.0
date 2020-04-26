package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundManagerRep;
import com.rong.tong.pfunds.module.view.TvPfundManagerRep;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundManagerRepMapper extends CommonBasicMapper<TbPfundManagerRep, TvPfundManagerRep>, MultiTableMapper<TbPfundManagerRep, TvPfundManagerRep> {
}
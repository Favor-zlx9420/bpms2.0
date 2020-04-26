package com.rong.tong.pfunds.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.tong.pfunds.module.entity.TbPfundResume;
import com.rong.tong.pfunds.module.view.TvPfundResume;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface PfundResumeMapper extends CommonBasicMapper<TbPfundResume, TvPfundResume>,MultiTableMapper<TbPfundResume,TvPfundResume> {
}
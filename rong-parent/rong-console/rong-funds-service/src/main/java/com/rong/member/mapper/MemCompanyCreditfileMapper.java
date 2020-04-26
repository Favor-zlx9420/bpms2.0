package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemCompanyCreditfile;
import com.rong.member.module.view.TvMemCompanyCreditfile;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemCompanyCreditfileMapper extends CommonBasicMapper<TbMemCompanyCreditfile, TvMemCompanyCreditfile>, MultiTableMapper<TbMemCompanyCreditfile, TvMemCompanyCreditfile> {
}
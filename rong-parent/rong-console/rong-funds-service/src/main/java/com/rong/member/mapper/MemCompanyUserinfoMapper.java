package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemCompanyUserinfo;
import com.rong.member.module.view.TvMemCompanyUserinfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemCompanyUserinfoMapper extends CommonBasicMapper<TbMemCompanyUserinfo, TvMemCompanyUserinfo>, MultiTableMapper<TbMemCompanyUserinfo, TvMemCompanyUserinfo> {
}
package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemPersonalUserinfo;
import com.rong.member.module.view.TvMemPersonalUserinfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemPersonalUserinfoMapper extends CommonBasicMapper<TbMemPersonalUserinfo, TvMemPersonalUserinfo>, MultiTableMapper<TbMemPersonalUserinfo, TvMemPersonalUserinfo> {
}
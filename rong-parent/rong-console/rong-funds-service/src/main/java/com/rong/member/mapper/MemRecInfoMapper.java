package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemRecInfo;
import com.rong.member.module.view.TvMemRecInfo;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemRecInfoMapper extends CommonBasicMapper<TbMemRecInfo, TvMemRecInfo>, MultiTableMapper<TbMemRecInfo, TvMemRecInfo> {
}
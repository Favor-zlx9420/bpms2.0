package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemGroup;
import com.rong.member.module.view.TvMemGroup;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemGroupMapper extends CommonBasicMapper<TbMemGroup, TvMemGroup>, MultiTableMapper<TbMemGroup, TvMemGroup> {
}
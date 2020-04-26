package com.rong.member.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.view.TvMemBase;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface MemBaseMapper extends CommonBasicMapper<TbMemBase, TvMemBase>, MultiTableMapper<TbMemBase, TvMemBase> {
}
package com.rong.sys.mapper;

import com.rong.common.mapper.CommonBasicMapper;
import com.rong.sys.module.entity.TbDictionary;
import com.rong.sys.module.view.TvDictionary;
import com.vitily.mybatis.core.mapper.MultiTableMapper;

public interface DictionaryMapper extends CommonBasicMapper<TbDictionary, TvDictionary>, MultiTableMapper<TbDictionary, TvDictionary> {
}
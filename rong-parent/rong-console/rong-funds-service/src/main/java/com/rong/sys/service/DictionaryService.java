package com.rong.sys.service;

import com.rong.common.service.BasicService;
import com.rong.sys.module.entity.TbDictionary;
import com.rong.sys.module.request.TqDictionary;
import com.rong.sys.module.view.TvDictionary;

public interface DictionaryService extends BasicService<TbDictionary, TqDictionary, TvDictionary> {
    Integer updateByBeans(TbDictionary[] dictionaries);
}
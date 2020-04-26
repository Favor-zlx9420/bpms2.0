package com.rong.cache.service;

import com.rong.cache.foreign.TbDictionary;
import com.rong.cache.service.impl.DictionaryCacheImpl;

import java.util.List;

public interface DictionaryCache extends BaseCache {
	void filter(DictionaryCacheImpl.DictionaryFilter filter);
	/**
	 * 单个更新:
	 */
	String setDictionary(TbDictionary dictionary);
	/**
	 * 获得字典值:直接从 map获取
	 * @param dictionaryKey 1
	 */
	String getValue(String dictionaryKey);

	/**
	 * 删除一个字典：一般不允许删除字典
	 * @param dictionaryKey 1
	 * @return 2
	 * @ 3
	 */
	String removeKey(String dictionaryKey);
	void init(List<TbDictionary> dictionaries);
}

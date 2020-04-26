package com.rong.cache.service;

import com.rong.common.consts.DictionaryKey;

/**
 * 公共缓存保存次数
 * 注意这里不继承
 * 
 * @author lether
 *
 */
public interface ComServiceFrequentCache{

	void setToServer(DictionaryKey.MemServiceKeyType type, String key);
	int getCache(DictionaryKey.MemServiceKeyType type, String key);
	void removeFromServer(DictionaryKey.MemServiceKeyType type, String key);
	ComServiceFrequentCache decorate(DictionaryKey.Keys k);
}

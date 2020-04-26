package com.rong.cache.service;

import com.rong.cache.base.CacheSerializable;
import com.rong.common.consts.DictionaryKey;

/**
 * 公共缓存：判断是否存在的依据
 * @author lether
 *
 */
public interface CommonServiceCache extends BaseCache {
	CommonServiceCache getInstance(DictionaryKey.Keys key, CacheSerializable cacheSerializable);
}

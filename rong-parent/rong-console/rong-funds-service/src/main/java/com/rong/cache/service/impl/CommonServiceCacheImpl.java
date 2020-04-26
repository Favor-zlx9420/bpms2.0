package com.rong.cache.service.impl;

import com.rong.cache.base.CacheSerializable;
import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.CommonServiceCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 公共缓存：判断是否存在的依据
 * @author lether
 *
 */
@Component
public class CommonServiceCacheImpl extends BaseCacheImpl implements CommonServiceCache {

	private static HashMap<DictionaryKey.Keys, CommonServiceCache> cacheMap=new HashMap<>();

	DictionaryCache dictionaryCache;
	ViyBasicCache viyCache;
	public CommonServiceCache getInstance(DictionaryKey.Keys key, CacheSerializable cacheSerializable){
		CommonServiceCache cache=cacheMap.get(key);
		if(CommonUtil.isNull(cache)){
			cache = new CommonServiceCacheImpl(key,dictionaryCache,viyCache,cacheSerializable);
			cacheMap.put(key, cache);
		}
		return cache;
	}
	@Autowired
	public CommonServiceCacheImpl(DictionaryCache dictionaryCache, ViyBasicCache viyBasicCache){
		super(DictionaryKey.Keys.OTHER.getValue(),null,dictionaryCache,viyBasicCache, CacheSerializableDelegate.build());
		this.dictionaryCache = dictionaryCache;
		this.viyCache = viyBasicCache;
	}
	/**
	 * 单一
	 */
	public CommonServiceCacheImpl(DictionaryKey.Keys key, DictionaryCache dictionaryCache, ViyBasicCache viyBasicCache, CacheSerializable cacheSerializable){
		super(key.getValue(),null,dictionaryCache,viyBasicCache,cacheSerializable);
		this.dictionaryCache=dictionaryCache;
		this.viyCache=viyBasicCache;
	}
}

package com.rong.cache.service.impl;

import com.rong.cache.base.CacheSerializable;
import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.ComServiceFrequentCache;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 公共缓存保存次数
 * 
 * @author lether
 *
 */
@Component
public final class ComServiceFrequentCacheImpl implements ComServiceFrequentCache {

	CommonServiceCache commonServiceCache;
	CacheSerializable cacheSerializable;
	private static volatile boolean init;

	@Autowired
	public ComServiceFrequentCacheImpl(CommonServiceCache commonServiceCache) {
		if(init){
			this.commonServiceCache = commonServiceCache;
		}else{
			init = true;
			this.commonServiceCache = commonServiceCache.getInstance(DictionaryKey.Keys.COMMON_CACHE_TIMES_DURATION, CacheSerializableDelegate.build());
		}
		this.cacheSerializable = CacheSerializableDelegate.build();
	}
	public ComServiceFrequentCache decorate(DictionaryKey.Keys k){
		return new ComServiceFrequentCacheImpl(commonServiceCache.getInstance(k,cacheSerializable));
	}
	private String getLastKey(DictionaryKey.MemServiceKeyType type, String key){
		return "FREQUENT_"+type.getValue() + "_count:" + key;
	}
	/**
	 *
	 */
	public void setToServer(DictionaryKey.MemServiceKeyType type, String key){
		String lastKey=getLastKey(type,key);
		Integer count= NumberUtil.toInteger(this.commonServiceCache.getFromServer(lastKey,String.class));
		if(CommonUtil.isNull(count)){
			count = 0;
		}
		++count;
		this.commonServiceCache.setToServer(lastKey, count);
	}
	public int getCache(DictionaryKey.MemServiceKeyType type, String key){
		String lastKey=getLastKey(type,key);
		Integer count= NumberUtil.toInteger(this.commonServiceCache.getFromServer(lastKey,String.class));
		if(CommonUtil.isNull(count)){
			count = 0;
		}
		return count;
	}
	public void removeFromServer(DictionaryKey.MemServiceKeyType type, String key){
		String lastKey=getLastKey(type,key);
		this.commonServiceCache.removeFromServer(lastKey);
	}
}

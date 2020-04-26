package com.rong.cache.service.impl;

import com.rong.cache.base.CacheSerializable;
import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.service.BaseCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用缓存：服务器
 * @author lether
 *
 */
@Slf4j
public abstract class BaseCacheImpl implements BaseCache {
	public static final int COMMON_CACHE_DEFAULT_VILITY_SECONDS=60;
	/**
	 * 前置key：除了字典缓存，一般是字典表里的key
	 */
	private String preKey;
	private String split;
	/**
	 * 保存时长(秒)：如果为null 则从字典缓存里取数据，如果再为null，则设置为COMMON_CACHE_DEFAULT_VALITY_SECONDS：公共默认缓存时长
	 */
	private Integer seconds;
	protected DictionaryCache dicCache;
	protected ViyBasicCache viyBasicCache;
	protected CacheSerializable cacheSerializable;
	public String getPreKey() {
		return preKey + split;
	}
	public Integer getSeconds() {
		if(CommonUtil.isNull(seconds)){
			Integer _seconds = COMMON_CACHE_DEFAULT_VILITY_SECONDS;
			try{
				_seconds = NumberUtil.toInteger(dicCache.getValue(this.preKey));
				if(CommonUtil.isNull(_seconds)){
					_seconds = COMMON_CACHE_DEFAULT_VILITY_SECONDS;
				}
			} catch (Exception e) {
				log.error("系统异常",e);
			}
			return _seconds;//每次都需要重新获取因为是单例模式
		}
		return seconds;
	}
    public DictionaryCache getDicCache() {
        return dicCache;
    }
	public ViyBasicCache getViyBasicCache() {
		return viyBasicCache;
	}
	public CacheSerializable getCacheSerializable(){
		return cacheSerializable;
	}
	public void setViyBasicCache(ViyBasicCache viyBasicCache) {
		this.viyBasicCache = viyBasicCache;
	}

	/**
	 * seconds为null时 dictionaryCache必不为null否则报错
	 * @param preKey 1
	 * @param seconds 2
	 * @param dictionaryCache 3
	 * @param viyBasicCache 4
	 */
	protected BaseCacheImpl(String preKey, Integer seconds, DictionaryCache dictionaryCache, ViyBasicCache viyBasicCache, CacheSerializable cacheSerializable){
		this(preKey,seconds,dictionaryCache,viyBasicCache,":");
		this.cacheSerializable = cacheSerializable;
	}
	/**
	 * seconds为null时 dictionaryCache必不为null否则报错
	 * @param preKey 1
	 * @param seconds 2
	 * @param dictionaryCache 3
	 * @param viyBasicCache 4
	 */
	private BaseCacheImpl(String preKey, Integer seconds, DictionaryCache dictionaryCache, ViyBasicCache viyBasicCache, String split){
		this.preKey=preKey;
		this.seconds=seconds;
		this.dicCache = dictionaryCache;
		this.viyBasicCache = viyBasicCache;
		this.split = split;
	}

	@Override
	public void setToServer(String key,Object value){
		viyBasicCache.put(getPreKey() + key, cacheSerializable.serialize(value), getSeconds(),false);
	}
	@Override
	public void setToServer(String key,Object value,Integer seconds){
		viyBasicCache.put(getPreKey() + key, cacheSerializable.serialize(value), seconds,false);
	}
	@Override
	public <T> T getFromServer(String key,Class<T> tClass){
		return cacheSerializable.unserialize(viyBasicCache.get(getPreKey()+key),tClass);
	}
	/**
	 * 只更新值，不更新失效时间
	 * @param key
	 * @param value
	 */
	@Override
	public void upToServer(String key,Object value){
		viyBasicCache.update(getPreKey() + key,cacheSerializable.serialize(value),false);
	}
	@Override
	public void removeFromServer(String key){
		viyBasicCache.remove(getPreKey()+key);
	}
	@Override
	public boolean existsInServer(String key){
		boolean res = false;
		try{
			res =  viyBasicCache.exists(getPreKey()+key);
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
		return res;
	}
	@Override
	public String hmset(String key,Map<String, ?> map){
		Map<String,Object> smap = new HashMap<>();
		for(Map.Entry<String,?> entry:map.entrySet()){
			smap.put(entry.getKey(),cacheSerializable.serialize(entry.getValue()));
		}
		return viyBasicCache.hmset(getPreKey()+key, smap);
	}
	@Override
	public <T> Map<String, T> hgetAll(String key,Class<T> tClass) {
		Map<String,?> map = viyBasicCache.hgetAll(getPreKey() + key);
		Map<String,T> omap = new HashMap<>();
		for(Map.Entry<String,?> entry:map.entrySet()){
			omap.put(entry.getKey(),cacheSerializable.unserialize(entry.getValue(),tClass));
		}
		return omap;
	}
	@Override
	public Long hdel(String key,String field){
		return viyBasicCache.hdel(getPreKey()+key, field);
	}
	@Override
	public <T> T hget(String key,String field,Class<T> tClass){
		return cacheSerializable.unserialize(viyBasicCache.hget(getPreKey()+key, field),tClass);
	}
	@Override
	public Long hset(String key,String field,Object value){
		return viyBasicCache.hset(getPreKey()+key, field, cacheSerializable.serialize(value));
	}
}

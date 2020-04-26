package com.rong.cache.service;


import java.util.Map;

/**
 * 通用缓存
 * @author lether
 *
 */
public interface BaseCache {
	void setToServer(String key, Object value);
	void setToServer(String key, Object value, Integer seconds);
	<T> T getFromServer(String key, Class<T> tClass);
	/**
	 * 只更新值，不更新失效时间
	 * @param key
	 * @param value
	 */
	void upToServer(String key, Object value);
	void removeFromServer(String key);
	boolean existsInServer(String key);
//=====================map操作=============================//
	String hmset(String key, Map<String, ?> map);
	<T> Map<String, T> hgetAll(String key, Class<T> tClass);
	Long hdel(String key, String field);
	<T> T hget(String key, String field, Class<T> tClass);
	Long hset(String key, String field, Object value);
}

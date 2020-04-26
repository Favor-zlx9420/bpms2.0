package com.rong.cache.service;

/**
 * apiToken缓存相关
 * @version
 * @author:  lether
 * @创建时间: 2017-07-21
 */
public interface SessionCache extends BaseCache {
	<T> T getSession(String sessionKey, Class<T> tClass);
	boolean setSession(String sessionKey, Object value);
	boolean removeSession(String sessionKey);
}

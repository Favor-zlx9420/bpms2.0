package com.rong.cache.service.impl;

import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.service.ComServiceFrequentCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.cache.service.JsonCacheSerializable;
import com.rong.cache.service.SessionCache;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * session
 * @author lether
 *
 */
@Component
@Slf4j
public final class SessionCacheImpl extends BaseCacheImpl implements SessionCache {

	@Autowired
	private SessionCacheImpl(DictionaryCache dictionaryCache, ComServiceFrequentCache comServiceFrequentCache, ViyBasicCache viyBasicCache){
		//null使用字典缓存里的时长
		super(DictionaryKey.Keys.SESSION_DURATION.getValue(),null,dictionaryCache,viyBasicCache,
				new JsonCacheSerializable());
	}

	@Override
	public <T> T getSession(String sessionKey,Class<T> tClass) {
		if(StringUtil.isEmpty(sessionKey)){
			return null;
		}
		T result = null;
		try{
			result = super.getFromServer(sessionKey,tClass);
		}catch (Exception e){
			log.warn(e.getMessage(),e);
		}
		return result;
	}

	@Override
	public boolean setSession(String sessionKey,Object value) {
		if(StringUtil.isEmpty(sessionKey)){
			return false;
		}
		try{
			super.setToServer(sessionKey,value);
		}catch (Exception e){
			log.warn(e.getMessage(),e);
			return false;
		}
		return true;
	}

	@Override
	public boolean removeSession(String sessionKey) {
		try{
			super.removeFromServer(sessionKey);
		}catch (Exception e){
			log.warn(e.getMessage(),e);
			return false;
		}
		return true;
	}
}

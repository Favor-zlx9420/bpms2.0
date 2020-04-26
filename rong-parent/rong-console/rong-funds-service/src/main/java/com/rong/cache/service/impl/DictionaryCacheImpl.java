package com.rong.cache.service.impl;

import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.foreign.TbDictionary;
import com.rong.cache.service.CacheSerializableDelegate;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.NoPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统字典
 *	serizable = String
 * @author lether
 *
 */
@Component
@Slf4j
public final class DictionaryCacheImpl extends BaseCacheImpl implements DictionaryCache {
	List<DictionaryFilter> filters;
	public void filter(DictionaryFilter filter){
		if(this.filters == null){
			this.filters = new ArrayList<>();
		}
		this.filters.add(filter);
	}
	@Autowired
	private DictionaryCacheImpl(ViyBasicCache viyBasicCache){
		//第三个参数必须为null
		super(DictionaryKey.Keys.SYSTEMDICTIONARY_NOSQL.getValue(),60 * 60 * 24 * 30 * 12,null,viyBasicCache,
				CacheSerializableDelegate.build());
	}
	public void init(List<TbDictionary> dictionaries){
		for(TbDictionary e:dictionaries){
			setDictionary(e);
		}
	}
	/**
	 * 后台跟api服务器redis一个集群：下直接从集群中获取
	 */
	@Override
	public String getValue(String dictionaryKey) {
		return super.hget("KEYS",dictionaryKey,String.class);
	}
	@Override
	public void setToServer(String key,Object value){
		throw new NoPermissionException();
	}
	/**
	 * 字典设置：
	 */
	@Override
	public String setDictionary(TbDictionary entity) {
		//viyBasicCache.publishMulToMul(DictionaryKey.ViyCacheSubstrTopic.更新字典.getValue(), JSONUtil.toJSONString(entity));
		if(!filterOpera(entity.getKey(), OperaType.EDIT)){
			return "forbidden";
		}
		super.hset("KEYS",entity.getKey(),entity.getValue());
		return CommonEnumContainer.ResultStatus.NORMAL.getValue();
	}
	@Override
	public void removeFromServer(String key){
		throw new NoPermissionException();
	}
	/**
	 * 删除一个字典：客户端无权限
	 * @param dictionaryKey 3
	 * @return 1
	 */
	public String removeKey(String dictionaryKey) {
		if(!filterOpera(dictionaryKey, OperaType.REMOVE)){
			return "forbidden";
		}
		super.hdel("",dictionaryKey);
		return "ok";
	}

	public enum OperaType {
		ADD,
		EDIT,
		REMOVE,
		;
	}
	public interface DictionaryFilter{
		boolean filterDictionary(String key, OperaType operaType);
	}

	/**
	 * 是否允许操作
	 * @param key
	 * @param operaType
	 * @return false 不允许继续操作;true：允许继续操作
	 */
	private boolean filterOpera(String key, OperaType operaType){
		if(filters == null){
			return true;
		}
		for(DictionaryFilter filter:filters){
			if(!filter.filterDictionary(key,operaType)){
				return false;
			}
		}
		return true;
	}
}

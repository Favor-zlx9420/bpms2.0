package com.rong.cache.service.impl;

import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.foreign.TmApiToken;
import com.rong.cache.service.*;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.CustomerException;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.GUIDGenerator;
import com.rong.common.util.MD5;
import com.rong.common.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商户token每次请求都延长x分钟，当x分钟不获取自动取消
 * @author lether
 *
 */
@Component
@Slf4j
public final class ApiTokenCacheImpl extends BaseCacheImpl implements ApiTokenCache {

	private ComServiceFrequentCache comServiceFrequentCache;
	@Autowired
	private ApiTokenCacheImpl(DictionaryCache dictionaryCache, ComServiceFrequentCache comServiceFrequentCache, ViyBasicCache viyBasicCache){
		//null使用字典缓存里的时长
		super(DictionaryKey.Keys.APITOKEN_IS_KEPT_FOR_A_LONG_TIME.getValue(),null,dictionaryCache,viyBasicCache, CacheSerializableDelegate.build());
		this.comServiceFrequentCache = comServiceFrequentCache.decorate(DictionaryKey.Keys.APITOKEN_IS_KEPT_FOR_A_LONG_TIME);
	}
	/**
	 * 获取apitoken：所有接口必须先获取token，然后每次调用接口都需要请求token方可进行调用。
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	@Override
	public TmApiToken getApiToken(String ip)throws Exception{
		if (CommonUtil.isNotNull(ip)) {
			log.info("request ip address:" + ip);
			String hip = MD5.getMD5(ip);
			int perIpMaxCount = NumberUtil.toInteger(getDicCache().getValue(DictionaryKey.Keys.EACH_IP_CAN_HAVE_A_MAXIMUM_NUMBER_OF_TOKENS_AT_THE_SAME_TIME.getValue()));
			int count = comServiceFrequentCache.getCache(DictionaryKey.MemServiceKeyType.EACH_IP_CAN_HAVE_A_MAXIMUM_NUMBER_OF_TOKENS_AT_THE_SAME_TIME, hip);
			if (count >= perIpMaxCount) {
				throw new CustomerException("该IP在同一时间内请求次数过多token，请使用以前有效的token", CommonEnumContainer.ResultStatus.TOO_MANY_REQUESTS);
			}
			//设置次数
			comServiceFrequentCache.setToServer(DictionaryKey.MemServiceKeyType.EACH_IP_CAN_HAVE_A_MAXIMUM_NUMBER_OF_TOKENS_AT_THE_SAME_TIME, hip);
			TmApiToken token = new TmApiToken();
			token.setApiToken(MD5.getMD5(GUIDGenerator.getGUID()));
			token.setLiveSeconds(getSeconds());
			super.setToServer(token.getApiToken(), ip);

			return token;
		}
		return null;
	}
	@Override
	public boolean isValidAuthToken(String apiToken)throws Exception {
		return super.existsInServer(apiToken);
	}
}

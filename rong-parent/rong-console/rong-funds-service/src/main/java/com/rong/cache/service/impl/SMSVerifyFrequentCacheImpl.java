package com.rong.cache.service.impl;

import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.foreign.SMSVerifyCodeFrequent;
import com.rong.cache.service.DictionaryCache;
import com.rong.cache.service.JsonCacheSerializable;
import com.rong.cache.service.SMSVerifyFrequentCache;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 手机验证码频繁缓存
 *
 * @author lether
 */
@Component
public final class SMSVerifyFrequentCacheImpl extends BaseCacheImpl implements SMSVerifyFrequentCache {

    @Autowired
    private SMSVerifyFrequentCacheImpl(DictionaryCache dictionaryCache, ViyBasicCache viyBasicCache) {
        //存一天:以最有一次为保存标准
        super(DictionaryKey.Keys.MEMBERS_CAN_SEND_SMS_NUMBER_OF_TIMES_PER_DAY.getValue(), 60 * 60 * 24, dictionaryCache, viyBasicCache, new JsonCacheSerializable());
    }

    /**
     * 更新调用次数至本机：客户端无权限
     * 否则依次叠加
     *
     * @param key     1
     * @param content :短信内容：每次都会改的
     */
    public void setToServer(String key, String content) {
        SMSVerifyCodeFrequent frequent = getFromServer(key, SMSVerifyCodeFrequent.class);
        if (frequent == null) {//说明还没调用过
            frequent = new SMSVerifyCodeFrequent();
        }
        frequent.setCount(frequent.getCount() + 1);
        frequent.setMillTime(System.currentTimeMillis());//
        frequent.setContent(content);
        super.setToServer(key, frequent);
    }

    @Override
    public void upErrorCount(String key) {
        SMSVerifyCodeFrequent frequent = getFromServer(key, SMSVerifyCodeFrequent.class);
        if (frequent == null) {//说明还没调用过
        	return;
        }
        frequent.setErrorCount(frequent.getErrorCount() + 1);
        setToServer(key, frequent);
    }
}

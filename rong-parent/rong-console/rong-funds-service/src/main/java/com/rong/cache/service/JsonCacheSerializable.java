package com.rong.cache.service;

import com.rong.cache.base.CacheSerializable;
import com.rong.common.util.JSONUtil;

public class JsonCacheSerializable implements CacheSerializable {
    @Override
    public <T> String serialize(T o) {
        return JSONUtil.toJSONString(o);
    }

    @Override
    public <T> T unserialize(Object object, Class<T> clazz) {
        if(null == object){
            return null;
        }
        return JSONUtil.parseObject(object.toString(),clazz);
    }
}

package com.rong.cache.base.impl;

import com.rong.cache.base.CacheSerializable;

public class OriginCacheSerializable implements CacheSerializable {
    @Override
    public <T> String serialize(T o) {
        return o != null ? o.toString():null;
    }

    @Override
    public <T> T unserialize(Object object, Class<T> clazz) {
        return (T)object;
    }
}

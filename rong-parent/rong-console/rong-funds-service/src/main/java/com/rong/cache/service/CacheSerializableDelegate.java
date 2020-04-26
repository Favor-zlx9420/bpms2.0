package com.rong.cache.service;

import com.rong.cache.base.CacheSerializable;
import com.rong.cache.base.impl.OriginCacheSerializable;

public abstract class CacheSerializableDelegate {
    private final static CacheSerializable jsonCacheSerializable = new JsonCacheSerializable();
    private final static CacheSerializable originCacheSerializable = new OriginCacheSerializable();
    public static final CacheSerializable build(){
        return originCacheSerializable;
    }
    public static final CacheSerializable jsonSeriaze(){
        return jsonCacheSerializable;
    }
}

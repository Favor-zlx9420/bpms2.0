package com.rong.cache.base;

public interface CacheSerializable {
    <T> String serialize(T o);
    <T> T unserialize(Object object, Class<T> clazz);
}

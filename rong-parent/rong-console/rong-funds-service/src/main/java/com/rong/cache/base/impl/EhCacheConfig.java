package com.rong.cache.base.impl;

import lombok.Data;

import java.io.Serializable;

@Data
public class EhCacheConfig implements Serializable{
    private String name = "default_cache";
    private Integer maxElementsInMemory = 20000;
    private Integer maxElementsOnDisk = 200000;
    private String memoryStoreEvictionPolicy = "LFU";
    private Long timeToLiveSeconds = 604800L;
    private Long timeToIdleSeconds = 30L;
    private Long diskExpiryThreadIntervalSeconds = 120L;
}

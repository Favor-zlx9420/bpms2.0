package com.rong.cache.base.impl;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedisConnectConfig implements Serializable {
    private String host;
    private Integer port;
    private Integer soTimeout;
    private String password;
    private int database;
}

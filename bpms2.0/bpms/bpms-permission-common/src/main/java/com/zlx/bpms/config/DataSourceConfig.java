package com.zlx.bpms.config;

import com.zlx.bpms.properties.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Package: com.zlx.bpms.config
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:数据库配置
 */
@Configuration
public class DataSourceConfig {

    @Resource
    private DataSourceProperties properties;


    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(properties.getDriverClassName());
        source.setUrl(properties.getUrl());
        source.setUsername(properties.getUsername());
        source.setPassword(properties.getPassword());
        return source;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }
}

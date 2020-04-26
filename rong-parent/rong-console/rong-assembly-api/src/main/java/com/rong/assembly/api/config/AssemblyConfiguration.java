package com.rong.assembly.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.base.impl.EhCacheConfig;
import com.rong.cache.base.impl.EhcacheImpl;
import com.rong.cache.base.impl.JedisImpl;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.thread_pools.ScheduledExecutorPool;
import com.rong.common.util.thread_pools.ThreadExecutorPool;
import com.vitily.mybatis.core.config.JdbcConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.sql.DataSource;

/**
 * creator : whh-lether
 * date    : 2019/6/26 18:37
 * desc    :
 **/
@Configuration
@EnableAsync
@ComponentScan({"com.rong.*.service","com.rong.tong.*.service","com.rong.common.util.config"})
@MapperScan(basePackages = {"com.rong.*.mapper","com.rong.tong.*.mapper","com.rong.assembly.api.mapper"})
public class AssemblyConfiguration {
    @Bean()
    public ThreadExecutorPool executor(){
        ThreadExecutorPool.loadConfig();
        return ThreadExecutorPool.getInstance();
    }
    @Bean
    public ScheduledExecutorPool scheduledExecutorPool(){
        return ScheduledExecutorPool.getInstance();
    }

    @Bean
    public ViyBasicCache viyBasicCache(ThreadExecutorPool executor,
                                       RedisProperties redisProperties,
                                       @Value("${application.cache-frame}")String cacheFrame){
        return CommonUtil.isEqual("redis", cacheFrame) ?
                JedisImpl.getInstance(redisProperties,executor) :
                EhcacheImpl.getInstance(new EhCacheConfig(),executor);
    }
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        JSONUtil.wrapLongToStringObjectMapper(objectMapper);
        return objectMapper;
    }
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource);
        sessionFactory.setConfiguration(new JdbcConfiguration());
        return sessionFactory.getObject();
    }
}

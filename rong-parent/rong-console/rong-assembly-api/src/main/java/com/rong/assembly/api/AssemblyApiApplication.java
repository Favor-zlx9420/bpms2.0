package com.rong.assembly.api;

import com.rong.common.util.AllinpaySecure;
import com.rong.product.fact.ProductFactory;
import encryption.STSTxData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
public class AssemblyApiApplication {

    @Value("${pub.path}")
    private String pubPath;
    @Value("${pri.path}")
    private String priPath;
    @Value("${pri.key.password}")
    private String priKeyPassword;

    @Value("${pub.h5.path}")
    private String pubH5Path;
    @Value("${pri.h5.path}")
    private String priH5Path;

    public static void main(String[] args) {
        SpringApplication.run(AssemblyApiApplication.class, args);
    }

    /**
     * 启动时，初始化加载通联接口认证证书
     */
    @Bean
    public void initSysCert() {
        // 通联API 接口初始化
        STSTxData.initSysCert(pubPath, priPath, priKeyPassword);
        // 通联H5认证初始化
        AllinpaySecure.initSysCert(priH5Path, pubH5Path);
    }


}

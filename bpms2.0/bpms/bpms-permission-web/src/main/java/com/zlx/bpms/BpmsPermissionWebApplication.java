package com.zlx.bpms;

import com.zlx.bpms.properties.BpmsSecurityProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.zlx.bpms.dao")
@EnableConfigurationProperties({BpmsSecurityProperties.class})
public class BpmsPermissionWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpmsPermissionWebApplication.class, args);
    }

}

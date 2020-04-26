package com.rong.console.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
public class BackApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }
}

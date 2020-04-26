package com.rong.assembly.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
//    @Bean
//    public Docket ProductApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .genericModelSubstitutes(DeferredResult.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(false)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.rong.assembly.api.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }

    //1，验证中心
    @Bean
    public Docket authCenter() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("验证中心")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rong.assembly.api.controller.auth"))
                .paths(PathSelectors.any())
                .build();
    }
    //2，内容中心
    @Bean
    public Docket infoCenter() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("内容中心")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rong.assembly.api.controller.business"))
                .paths(PathSelectors.any())
                .build();
    }
    //3，直营店
    @Bean
    public Docket storeCenter() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("直营店")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rong.assembly.api.controller.store"))
                .paths(PathSelectors.any())
                .build();
    }
    //3，会员中心（包括客服、机构代理、基金经理、投资用户）
    @Bean
    public Docket userCenter() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("会员中心（包括客服、机构代理、基金经理、投资用户）")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rong.assembly.api.controller.usercenter"))
                .paths(PathSelectors.any())
                .build();
    }
    //4，名片中心
    @Bean
    public Docket userCardCenter() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("名片中心")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rong.assembly.api.controller.usercard"))
                .paths(PathSelectors.any())
                .build();
    }

    //5，开户相关
    @Bean
    public Docket userOpenAccount() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("用户开户")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rong.assembly.api.controller.openaccount"))
                .paths(PathSelectors.any())
                .build();
    }

    //6 产品管理
    @Bean
    public Docket productManager() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("产品管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rong.assembly.api.controller.product"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api center")
                .description("say the world")
                .termsOfServiceUrl("http://localhost/")
                .contact("zhanglei")
                .version("1.0.6")
                .build();
    }
}

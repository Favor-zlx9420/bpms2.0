package com.zlx.bpms.config;

import com.zlx.bpms.UserDetailServiceImpl;
import com.zlx.bpms.properties.BpmsSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @Package: com.zlx.bpms.config
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:bpms安全配置
 */
@Configuration
@Order(-1) //值越小，优先级越高
@EnableWebSecurity
public class BpmsSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private BpmsSecurityProperties properties;
    @Resource
    private UserDetailServiceImpl detailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 放行路径
        String[] releaseUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getReleaseUrl(), ",");
        //免认证静态资源路径
        String[] anonResources = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonResourcesUrl(), ",");
        http
                //开启http基本配置
                .httpBasic()
                .and()
                //开启表单登陆方式
                .formLogin()
                //登陆的Url地址
                .loginPage(properties.getLoginUrl())
                //处理登陆认证URL（页面的action属性值）
                .loginProcessingUrl(properties.getLoginProcessingUrl())
                //放行登陆页面
                .permitAll()
                .and()
                //开启授权配置
                .authorizeRequests()
                //放行路径
                .antMatchers(releaseUrl).permitAll()
                //免认证静态资源路径
                .antMatchers(anonResources).permitAll()
                //所有请求
                .anyRequest()
                //都需要认证
                .authenticated();
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //设置密码编码器
        provider.setPasswordEncoder(passwordEncoder());
        //设置 用户详细信息服务接口
        provider.setUserDetailsService((UserDetailsService) detailService);
        // 关闭 隐藏未找到用户异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    /**
     * 身份验证管理器
     *
     * @param auth 身份验证管理器生成器
     * @throws Exception 异常信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //目的是为了前端获取数据时获取到整个form-data的数据,提供验证器
        auth.authenticationProvider(authenticationProvider());
        //配置登录user验证处理器  以及密码加密器  好让认证中心进行验证
        auth.userDetailsService((UserDetailsService) detailService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        String[] anonResources = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonResourcesUrl(), ",");
        web.ignoring().antMatchers(HttpMethod.POST, anonResources);
    }
}

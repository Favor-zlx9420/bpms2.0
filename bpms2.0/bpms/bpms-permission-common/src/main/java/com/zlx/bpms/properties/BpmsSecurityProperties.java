package com.zlx.bpms.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Package: com.zlx.bpms.properties
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:权限认证属性
 */
@ConfigurationProperties(prefix = "spring.security")
@Data
public class BpmsSecurityProperties {

    /**
     * 登录路径
     */
    private String loginUrl;
    /**
     * 登出路径
     */
    private String logoutUrl;
    /**
     * 免认证静态资源路径
     */
    private String anonResourcesUrl;
    /**
     * 放行路径
     */
    private String releaseUrl;
    /**
     * 记住我超时时间
     */
    private int rememberMeTimeout;

    /**
     *  处理登陆认证URL（页面的action属性值）
     */
    private String loginProcessingUrl;
}

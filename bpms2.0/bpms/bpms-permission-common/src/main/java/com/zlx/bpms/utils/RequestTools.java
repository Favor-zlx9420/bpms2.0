package com.zlx.bpms.utils;

import com.zlx.bpms.bean.User;
import com.zlx.bpms.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.zlx.bpms.util
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:请求工具
 */
public class RequestTools {

    private static UserService userService = (UserService) SpringContextTools.getBean("userService");

    public static final String ADMIN_USER_NAME = "admin";

    /**
     * 获取操作人名称
     *
     * @return name
     */
    public static String request() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }

    /**
     * 获得请求路径
     */
    public static String getRequestPath(HttpServletRequest request, boolean isParam) {
        String requestPath = request.getRequestURI();
        if (isParam) {
            String queryParam = request.getQueryString();
            if (StringTools.isNotEmpty(queryParam)) {
                requestPath += "?" + queryParam;
            }
            if (requestPath.indexOf("&") > -1) {// 去掉其他参数
                requestPath = requestPath.substring(0, requestPath.indexOf("&"));
            }
        }
        if (requestPath.indexOf(";") > -1) {// 去掉其他参数
            requestPath = requestPath.substring(0, requestPath.indexOf(";"));
        }
        requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
        return requestPath;
    }

    /**
     * 获取管理用户ID
     *
     * @return id
     */
    public static Integer getAdminUserId() {
        return getUserEntity(ADMIN_USER_NAME).getId();
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public static Integer getUserId() {
        return getUserEntity(request()).getId();
    }

    /**
     * 获取用户实体
     *
     * @param username 用户名
     * @return user
     */
    public static User getUserEntity(String username) {
        return userService.getUserByUserName(username);
    }


}

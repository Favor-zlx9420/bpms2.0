package com.zlx.bpms.service;

import com.zlx.bpms.bean.User;

import java.util.List;

/**
 * @Package: com.zlx.bpms.service
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:权限认证用户接口服务
 */
public interface UserService {
    /**
     * 获取用户信息 by 用户名
     *
     * @param username 用户名
     * @return User
     */
    User getUserByUserName(String username);

    /**
     * 查找角色权限 by 用户名
     *
     * @param username 用户名
     * @return list
     */
    List<String> findRolePermission(String username);
}

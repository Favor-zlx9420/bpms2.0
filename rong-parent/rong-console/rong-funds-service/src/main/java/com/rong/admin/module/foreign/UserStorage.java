package com.rong.admin.module.foreign;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserStorage implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 	用户id
     */
    private long id;
    /**
     * 用户名称：登录名
     */
    private String name;
    /**
     * 显示名称:
     */
    private String showName;

    /**
     * 临时token
     * 加密token md5（salt＋id＋token）
     */
    private String authToken;

    /**
     * 权限字符窜列表：id权限方式
     */
    private Set<Long> permissions;
}

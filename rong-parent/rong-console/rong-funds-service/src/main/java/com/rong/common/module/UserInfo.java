package com.rong.common.module;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserInfo<T> implements Serializable{
    /**
     * token
     */
    @ApiParam(hidden = true)
    private String token;
    /**
     * 用户名称
     */
    @ApiParam(value = "用户名称",hidden = true)
    private String userName;
    /**
     * 用户id
     */
    @ApiParam(hidden = true)
    private Long userId;
    /**
     * token过期时间戳：到了这个时间就自动过期
     */
    @ApiParam(value = "token过期时间戳：到了这个时间就自动过期",hidden = true)
    private Long expire;
    @ApiParam(hidden = true)
    private List<String> roles;//所属角色
    @ApiParam(hidden = true)
    private T userInfo;
    @ApiParam(value = "是否已进行过合格投资人认证",hidden = true)
    private boolean qualified = false;
    @ApiParam(value = "是否已设置密码",hidden = true)
    private boolean hasSetPassword = false;

    @ApiParam(value = "我的账本总览",hidden = true)
    private Object fundAccount0;


}

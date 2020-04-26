package com.rong.admin.module.req;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.TqBase;
import lombok.Data;

@Data
public class TqUserLogin extends TqBase {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录模式
     */
    private CommonEnumContainer.UserLoginMode loginMode;
    /**
     * 登录用户名：可能也通过phone号码等登录
     */
    private String userName;
    /**
     * 登录密码：第一次加密的密码。
     */
    private String password;
    /**
     * 第三方登录：如cookie等需要token验证
     */
    private String token;
    /**
     * 保存时长：小时
     */
    private Integer storeTime;
    /**
     * userId
     */
    private long userId;
}

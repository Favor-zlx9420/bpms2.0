package com.rong.common.util;

import com.rong.common.module.UserInfo;

public abstract class ServiceEncryUtil {
    public static String responseUserToken(String username, String secret){
        return MD5.getMD5(username + "," + secret + "," + ServiceEncryUtil.class.getName());
    }
    public static String createUserToken(UserInfo userInfo){
        return MD5.getMD5(userInfo.getUserName() + "," + userInfo.getExpire() + "," + ServiceEncryUtil.class.getName());
    }
}

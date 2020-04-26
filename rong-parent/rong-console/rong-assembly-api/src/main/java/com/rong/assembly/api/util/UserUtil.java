package com.rong.assembly.api.util;

import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.module.UserInfo;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.ServiceEncryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public abstract class UserUtil {
    public final static String BEARER = "Bearer ";
    public static UserInfo getUserTokenFromAuthorization(CommonServiceCache memCache, String authorization){
        if (StringUtils.isEmpty(authorization) || !authorization.startsWith(BEARER) || authorization.length() <= BEARER.length()) {
            return null;
        }
        final String[] _split = authorization.substring(BEARER.length()).split("\\|");
        if (_split.length != 3) {
            return null;
        }
        final String authToken = _split[0];
        final String username = _split[1];
        final Long expires = Long.valueOf(_split[2]);
        if (expires <= System.currentTimeMillis()) {
            throw new CustomerException("token 无效", CommonEnumContainer.ResultStatus.INTERFACE_TOKEN_INVALID);
        }
        //验证token 的合法性（username+expire）
        if (!CommonUtil.isEqual(authToken, ServiceEncryUtil.responseUserToken(username,_split[2]))) {
            //throw new CustomerException("请不要修改token", CommonEnumContainer.ResultStatus.Token无效);
            throw new CustomerException("token 不合法", CommonEnumContainer.ResultStatus.INTERFACE_TOKEN_INVALID);
        }
        return memCache.getFromServer(authToken, UserInfo.class);
    }
}

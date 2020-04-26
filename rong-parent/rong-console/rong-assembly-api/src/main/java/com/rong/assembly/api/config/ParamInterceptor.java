package com.rong.assembly.api.config;

import com.rong.assembly.api.util.HttpUtil;
import com.rong.cache.service.CommonServiceCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.module.TqBase;
import com.rong.common.module.UserInfo;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.StringUtil;
import com.rong.member.util.MemberUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Order(value = 1)
@Aspect
@Component
@Slf4j
public class ParamInterceptor {
    @Autowired
    private CommonServiceCache commonServiceCache;
    @Pointcut("execution(* com.rong.assembly.api.controller.*.*.*(..))")
    public void controllerMethodPointcut() {}

    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint jp)throws Throwable{
        return checkTokenAndParam(jp);
    }

    private Object checkTokenAndParam(JoinPoint jp)throws Throwable{
        Object[] args = jp.getArgs();
        if (args.length < 1 || !(args[0] instanceof TqBase)) {
            log.info("接口开发方书写接口有误，请联系接口开发方.并提供详细信息,接口需要至少1个参数，第一个为 TqBase 的扩展类");
            return Result.miss(CommonEnumContainer.ResultStatus.PARAMETER_IS_NOT_COMPLETE,"接口开发方书写接口有误，请联系接口开发方.并提供详细信息");
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Object ar1 = args[0];//取得请求参数
        TqBase tqBase = (TqBase) ar1;
        tqBase.setReqIp(HttpUtil.getIP(request));
        //先定非认证，每次请求重新定义是否已认证。
        tqBase.setLoginUserId(-1L);
        if(StringUtil.isNotEmpty(tqBase.getUserAuthToken())){
            CommonServiceCache memCache = MemberUtil.getMemCache(commonServiceCache);
            UserInfo userInfo = memCache.getFromServer(tqBase.getUserAuthToken(), UserInfo.class);
            if(userInfo != null){
                if(userInfo.isQualified()) {
                    request.setAttribute("mastQualified", userInfo.isQualified());
                }
                tqBase.setLoginUserId(userInfo.getUserId());
                tqBase.setUserInfo(userInfo);
            }
        }
        //

        Result result = CommonUtil.isLegalParameter(ar1);
        if(result.sad()){
            return result;
        }
        return ((ProceedingJoinPoint) jp).proceed();
    }

}

package com.rong.assembly.api.config;

import com.rong.common.exception.NoLoginException;
import com.rong.common.module.TqBase;
import com.rong.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Aspect
@Component
@Slf4j
public class UserAuthInterceptor {
    @Pointcut("execution(* com.rong.assembly.api.controller.user*.*.*(..))")
    public void controllerMethodPointcut() {
    }

    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint jp)throws Throwable {
        return checkUserAuth(jp);
    }

    private Object checkUserAuth(JoinPoint jp)throws Throwable{
        Object[] args = jp.getArgs();
        if (args.length < 1 || !(args[0] instanceof TqBase)) {
            log.debug("非用户相关，略过");
            return ((ProceedingJoinPoint) jp).proceed();
        }
        TqBase tq = (TqBase) args[0];
        //判断token是否有效
        if (CommonUtil.isNull(tq.getUserInfo())) {
            throw new NoLoginException("用户未登录哟");
        }
        //CommonUtil.SystemPrintln("time:" + (System.currentTimeMillis() - now));
        //结束，返回指定值
        //CommonUtil.SystemPrintln("time:" + (System.currentTimeMillis() - now));
        return ((ProceedingJoinPoint) jp).proceed();
    }
}
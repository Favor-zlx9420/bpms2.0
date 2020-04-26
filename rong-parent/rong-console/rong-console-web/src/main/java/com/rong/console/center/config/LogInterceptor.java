package com.rong.console.center.config;

import com.rong.admin.module.entity.TbAdmLog;
import com.rong.admin.module.foreign.UserStorage;
import com.rong.admin.service.AdmColumnService;
import com.rong.admin.service.AdmLogService;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.NoPermissionException;
import com.rong.common.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
@Order(value = 8088)
@Slf4j
public class LogInterceptor {
    private final AdmLogService admLogService;
    private final AuthenticationInterceptor admAspect;
    private final AdmColumnService admColumnService;

    @Autowired
    public LogInterceptor(
            AdmLogService admLogService,
            AuthenticationInterceptor admAspect,
            AdmColumnService admColumnService
    ) {
        this.admLogService = admLogService;
        this.admAspect = admAspect;
        this.admColumnService = admColumnService;
    }

    //@annotation(org.springframework.web.bind.annotation.ResponseBody) &&
    //
    //@Pointcut("execution (* com.rong.sys.controller.*Controller.*(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,..))")
//	@Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody) && execution (* com.rong.sys.controller..*(..,com.rong.api_entity.req_entity.*.*))")
//	//@Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
//	public void reqAsect(){}
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "&& execution (* com.rong.console.center.controller..*(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,..)) " +
            "&& !execution(* com.rong.console.center.controller.BaseController.dataGrid(..))" +
            "&& !execution(* com.rong.console.center.controller.FundsBaseController.dataGrid(..))")
    public void queryAspect() {
    }

    public void log(Object returnValue, String actionPath, Object BaseSearch, HttpServletRequest request, CommonEnumContainer.LogState logState, Object[] args) throws Exception {
        TbAdmLog admLog = new TbAdmLog();
        admLog.setUri(actionPath);
        StringBuilder argb = new StringBuilder();
        for (int i = 2; i < args.length; ++i) {
            argb.append(JSONUtil.toJSONString(args[i])).append(",");
        }
        if (argb.length() > 0) {
            admLog.setArguments(argb.substring(0, argb.length() - 1));
        }
        admLog.setCreateDate(new Date());
        admLog.setDeltag(CommonEnumContainer.Deltag.NORMAL.getValue());
        admLog.setColumnId(admColumnService.hashAuthSingle(actionPath));
        admLog.setIp(com.rong.console.center.util.HttpUtil.getIP(request));
        admLog.setReturnValue(JSONUtil.toJSONString(returnValue));
        admLog.setTime(new Date());
        UserStorage userStorage = admAspect.getAdminByServerStorage(request);
        if (null != userStorage) {
            admLog.setAdmUserId(userStorage.getId());
            admLog.setAdmUserName(userStorage.getName());
        }
        admLog.setState(logState.getValue());
        admLogService.insertSelective(admLog);
        log.info("param:" + JSONUtil.toJSONString(BaseSearch));
    }

    /**
     * log先日志后权限
     *
     * @param pJoinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "queryAspect()", argNames = "pJoinPoint")
    public Object logs(ProceedingJoinPoint pJoinPoint) throws Throwable {
        Object[] args = pJoinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        String actionPath = request.getServletPath();
        Object returnValue = null;
        try {
            returnValue = pJoinPoint.proceed();
            log(returnValue, actionPath, args[2], request, CommonEnumContainer.LogState.NORMAL, args);
        } catch (Throwable e) {
            if (!(e instanceof NoPermissionException)) {
                returnValue = e.getMessage();
                //抛出应用程序异常也要记录
                log(returnValue, actionPath, args[2], request, CommonEnumContainer.LogState.ABNORMAL, args);
            }
            throw e;//抛出异常给其他逻辑处理
        }
        return returnValue;
    }
}

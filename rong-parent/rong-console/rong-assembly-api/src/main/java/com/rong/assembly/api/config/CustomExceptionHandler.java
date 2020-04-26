package com.rong.assembly.api.config;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.CustomerException;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
@RequestMapping(value = "error")
@Slf4j
public class CustomExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error/error";
    }

    @RequestMapping
    public Result error() {
        return Result.miss(CommonEnumContainer.ResultStatus.QUERY_DOES_NOT_EXIST,"资源找不到！[请求路径无法匹配]");
    }

    @ExceptionHandler({UndeclaredThrowableException.class,})
    public Result undeclaredThrowableException(HttpServletRequest request, Throwable e)throws Exception{
        log.error("UndeclaredThrowableException, URL = {" + request.getRequestURI() + "}",e);
        return CommonUtil.getResultByThrowable(e.getCause());
    }
    @ExceptionHandler({Exception.class})
    public Result exception(HttpServletRequest request, Throwable e)throws Exception{
        log.error("Exception, URL = {" + request.getRequestURI() + "}",e);
        return CommonUtil.getResultByThrowable(e);
    }
    //ClassCastException
    @ExceptionHandler({CustomerException.class,})
    public Result customerException(HttpServletRequest request, Throwable e)throws Exception{
        log.debug("CustomerException, URL = {" + request.getRequestURI() + "}");
        return CommonUtil.getResultByThrowable(e);
    }
    @ExceptionHandler({TypeMismatchException.class, BindException.class})
    public Result handleNumberFormatException(HttpServletRequest request, Throwable e){
        log.warn("TypeMismatchException, URL = {" + request.getRequestURI() + "}",e);
        return Result.miss(CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS,"请求参数不匹配，请检查");
    }
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result httpRequestMethodNotSupportedException(HttpServletRequest request, Throwable e){
        log.debug("HttpRequestMethodNotSupportedException, URL = {" + request.getRequestURI() + "}");
        log.debug(e.toString());
        return Result.miss(CommonEnumContainer.ResultStatus.PAGE_REQUEST_EXCEPTION,"不支持该请求方式["+request.getMethod()+"]，请使用正确的调用方式，详情咨询开发人员。");
    }
}

package com.rong.console.center.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rong.common.annotation.NoResponseAsResult;
import com.rong.common.module.Result;
import com.rong.common.util.JSONUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * creator : whh-lether
 * date    : 2019/6/20 9:28
 * desc    :
 **/
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class CustomResultHandler implements ResponseBodyAdvice {

    private ThreadLocal<ObjectMapper>  mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    private static final Class[] annos = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };

    /**
     * 对所有RestController的接口方法进行拦截
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno)) && !element.isAnnotationPresent(NoResponseAsResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object out;
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(body instanceof Result){
            out = body;
        }else if(body instanceof String){
            return JSONUtil.toJSONString(Result.success((String)body));
        }else{
            out = Result.success(body);
        }
        return out;
    }

}
package com.rong.assembly.api.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestParameterFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map paramter = new HashMap();
        paramter.put("userAuthToken",request.getHeader("userAuthToken"));
        ParameterRequestWrapper wrapper = new ParameterRequestWrapper(request, paramter);
        filterChain.doFilter(wrapper, response);
        return;
    }
}

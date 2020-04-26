package com.rong.product.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Package: com.rong.product.context
 * @Author: LQW
 * @Date: 2020/4/22
 * @Description:产品上下文
 */
@Component
public class ProductContext implements ApplicationContextAware {

    private ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}

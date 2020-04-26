package com.rong.product.annotation;

import com.rong.product.enumerate.ProductEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.rong.product.annotation
 * @Author: LQW
 * @Date: 2020/4/22
 * @Description:
 */
@Target(ElementType.TYPE)   //作用在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited  //子类可以继承此注解
public @interface ProductAnnotation {
    ProductEnum value();
}

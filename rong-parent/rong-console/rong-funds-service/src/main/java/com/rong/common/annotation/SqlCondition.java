package com.rong.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SqlCondition {
    String value()default "";
}

package com.rong.common.util;

import com.vitily.mybatis.core.cache.TableInfoHelper;
import com.vitily.mybatis.core.entity.TableMetaInfo;
import org.springframework.core.ResolvableType;

public abstract class TableMetaInfoUtil {

    public static TableMetaInfo getTableInfoFromControllerClass(Class<?> controllerClass) {
        Class<?> c = ResolvableType.forType(controllerClass.getGenericSuperclass()).getGeneric(0).getRawClass();
        return TableInfoHelper.getTableInfoFromEntityClass(c);
    }
}

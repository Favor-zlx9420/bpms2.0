package com.rong.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class CastUtil {
    public static List<Object> castArray(Object ov,Class<?> clazz){
        if(ov == null)
            return null;
        String[] vs = ov.toString().split("\\,");
        List<Object> values = new ArrayList<>(vs.length*2);
        for(String s:vs){
            Object v = cast(s,clazz);
            if(null != v) {
                values.add(v);
            }
        }
        return values;
    }
    public static Object cast(Object ov, Class<?> clazz){
        if(ov == null)
            return null;
        String value = ov.toString();
        if(StringUtil.isEmpty(value)){
            return null;
        }
        if(clazz == String.class){
            return value;
        }else if(clazz == Boolean.class){
            return Boolean.valueOf(value);
        }else if(clazz == Long.class){
            return Long.valueOf(value);
        }else if(clazz == Integer.class){
            return Integer.valueOf(value);
        }else if(clazz == BigDecimal.class){
            return new BigDecimal(value);
        }
        return value;
    }
}

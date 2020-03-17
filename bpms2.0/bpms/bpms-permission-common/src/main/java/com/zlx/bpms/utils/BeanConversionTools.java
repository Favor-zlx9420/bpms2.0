package com.zlx.bpms.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.zlx.bpms.utils
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:实体转换工具类
 */
public class BeanConversionTools {

    /**
     *  复制属性和超类(父类)属性
     * @param source    要复制的对象
     * @param target    复制后的对象
     * @throws IllegalAccessException
     */
    static void copyPropertiesAndSuperClassProperties(Object source, Object target) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //先拿到父类的及更高层次的属性
        Field[] fields = getAllFields(source);
        for (Field field : fields) {
            String key = field.getName();
            field.setAccessible(true);
            Object value = field.get(source);
            map.put(key, value);
        }
        target = MapTools.mapToObj(map, target);
    }

    /**
     *   获取对象中的所有字段
     * @param obj   对象
     * @return fields
     */
    static Field[] getAllFields(Object obj) {
        Class<?> clazz = obj.getClass();
        List<Field> list = new ArrayList<Field>();
        while (null != clazz) {
            list.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[list.size()];
        list.toArray(fields);
        return fields;
    }
}

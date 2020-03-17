package com.zlx.bpms.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Package: com.zlx.bpms.utils
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:map工具
 */
public class MapTools {


    /**
     * objToMap
     *
     * @param obj obj
     * @return map
     * @throws IllegalAccessException
     */
    static Map<String, Object> convertToMap(Object obj) throws IllegalAccessException {
        HashMap<String, Object> map = new HashMap<>(16);
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String key = field.getName();
            Object value = field.get(obj);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 通过固定大小键设置值
     *
     * @param map    map
     * @param values 键集
     */
    static void setValueByFixedSizeKey(LinkedHashMap<String, Object> map, List<String> values) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        for (String value : values) {
            result.put(value, "");
        }
        List<Map.Entry<String, Object>> oldValue = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
        List<Map.Entry<String, Object>> newValue = new ArrayList<Map.Entry<String, Object>>(map.entrySet());

        List<String> oldKeyList = new ArrayList<String>();
        List<String> newKeyList = new ArrayList<String>();

        for (Map.Entry<String, Object> en : oldValue) {
            oldKeyList.add(en.getKey());
        }

        for (Map.Entry<String, Object> en : newValue) {
            newKeyList.add(en.getKey());
        }

        LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();

        for (int i = 0; i < map.size(); i++) {
            newMap.put(oldKeyList.get(i), newKeyList.get(i));
        }

        map.clear();
        map.putAll(newMap);
    }


    /**
     * 通过集合 转换只带有键的map 适用场景：list集合中只有一个对象
     *
     * @param list 需要转map且是键的集合 例：[chage_item,old_value,new_value]
     * @return map
     */
    static LinkedHashMap<String, Object> setTheMapCollection(List<String> list) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if (list == null) return map;
        list.forEach(value -> {
            map.put(value, "");
        });
        return map;
    }

    /**
     * 重置 集合中的map值
     *
     * @param maps
     */
    static void resetMaps(Set<Map<String, Object>> maps) {
        maps.removeIf(map -> !"1".equals(map.get("chage_item").toString()) && !"2".equals(map.get("chage_item").toString()));
    }


    /**
     * 根据对象属性移除map键值对
     *
     * @param map
     * @param clazz
     * @return
     */
    static Object removeTheMapCollectionBasedOnObjPro(Map<String, Object> map, Class<Object> clazz) {
        String resultMapJson = JSON.toJSONString(map);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            map.remove(name);
        }
        return JSON.parseObject(resultMapJson, clazz);
    }

    /**
     * map转obj
     *
     * @param map map
     * @param obj obj
     * @return obj
     */
    static Object mapToObj(Map<String, Object> map, Object obj) {
        BeanMap beanMap = BeanMap.create(obj);
        beanMap.putAll(map);
        return obj;
    }

    /**
     * 将 对象集合转换为map集合
     *
     * @param objs list<obj></>
     * @return list<map></>
     * @throws IllegalAccessException
     */
    static List<Map<String, Object>> objsToMaps(List<Object> objs) throws IllegalAccessException {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objs != null && 0 < objs.size()) {
            Map<String, Object> map = null;
            Object obj = null;
            for (Object o : objs) {
                obj = o;
                map = convertToMap(obj);
                list.add(map);
            }
        }
        return list;
    }


    /**
     * 将 map集合转换为对象集合
     *
     * @param maps  list<map></>
     * @param clazz obj
     * @return list<obj></>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    static List<Object> mapsToObjs(List<Map<String, Object>> maps, Class<Object> clazz) throws IllegalAccessException, InstantiationException {
        ArrayList<Object> objs = new ArrayList<>();
        if (null != maps && 0 < maps.size()) {
            Map<String, Object> map = null;
            Object obj = null;
            for (Map<String, Object> mp : maps) {
                map = mp;
                obj = clazz.newInstance();
                mapToObj(map, obj);
                objs.add(obj);
            }
        }
        return objs;
    }
}

package com.zlx.bpms.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.bpms.auth.util
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:json工具
 */
public class JsonTools {

    /**
     * 对序列化的Long类型进行特殊处理,避免位数过大导致和js精度的丢失,只用于向页面发送json数据时使用
     */
    private static ObjectSerializer longSerializer = new ObjectSerializer() {
        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, java.lang.reflect.Type fieldType,
                          int features) throws IOException {
            SerializeWriter out = serializer.getWriter();
            if (object == null) {
                if (out.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
                    out.write('0');
                } else {
                    out.writeNull();
                }
                return;
            }
            out.writeString(object.toString());
        }
    };

    /**
     * 对Long型兼容js的json串
     */
    private static String toCompatibleJson(Object object, String format) {
        SerializeWriter out = new SerializeWriter();
        try {
            //此处必须new一个SerializeConfig,防止修改默认的配置
            JSONSerializer serializer = new JSONSerializer(out, new SerializeConfig());
            serializer.getMapping().put(Long.class, longSerializer);
            if (format != null) {
                serializer.getMapping().put(Date.class, new SimpleDateFormatSerializer(format));
            }
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    /**
     * 转换为指定的格式
     *
     * @param object obj对象
     * @return str
     */
    public static String toCompatibleJson(Object object) {
        return toCompatibleJson(object, null);
    }

    /**
     * 转化实体为json字符串
     */
    public static String toJson(Object obj, SerializerFeature... serializerFeature) {
        if (serializerFeature != null && serializerFeature.length > 0) {
            return JSON.toJSONString(obj, serializerFeature[0]);
        }
        return JSON.toJSONString(obj);
    }

    /**
     * 转化json字符串为实体
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        if ("".equals(json.trim())) {
            return null;
        }
        return JSON.parseObject(json, clazz);
    }

    /**
     * Json 转为List
     *
     * @param json  Json字符串
     * @param clazz 对象
     * @return list<>对象</>
     */
    public static <T> List<T> getList(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        if ("".equals(json.trim())) {
            return null;
        }
        return JSON.parseArray(json, clazz);
    }

    /**
     * 将Json字符串转化成Map对象
     *
     * @param json Json字符串
     * @return map
     */
    public static Map<Object, Object> fromJson(String json) {
        if (json == null) {
            return null;
        }
        if ("".equals(json.trim())) {
            return null;
        }
        return JSON.parseObject(json, new TypeReference<Map<Object, Object>>() {
        }, Feature.OrderedField);
    }

    /**
     * 来自Json List
     *
     * @param json json字符串
     * @return list
     */
    public static List<Map<Object, Object>> fromJsonList(String json) {
        if (json == null) {
            return null;
        }
        if ("".equals(json.trim())) {
            return null;
        }
        return JSON.parseObject(json, new TypeReference<List<Map<Object, Object>>>() {
        });
    }

}

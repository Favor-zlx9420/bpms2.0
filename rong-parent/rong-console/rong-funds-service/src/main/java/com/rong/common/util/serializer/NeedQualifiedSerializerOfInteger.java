package com.rong.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NeedQualifiedSerializerOfInteger extends JsonSerializer<Integer> {
    public static final ThreadLocal<Boolean> mastQualified = new InheritableThreadLocal<>();
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if(mastQualified.get() != null && mastQualified.get()){
            //gen.writeString("认证可见");
        }
    }
}

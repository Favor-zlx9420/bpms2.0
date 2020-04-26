package com.rong.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * simple to string
 */
@Slf4j
public class NeedQualifiedSerializer extends ToStringSerializer {
    protected void serializeNotNull(Object value, JsonGenerator gen, SerializerProvider provider)throws IOException{
        super.serialize(value,gen,provider);
    }
    protected void serializeNoAuth(Object value, JsonGenerator gen, SerializerProvider provider)throws IOException{
        gen.writeString("认证可见");
    }
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider)throws IOException
    {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        boolean mastQualified = request.getAttribute("mastQualified") != null;
        if(!mastQualified){
            serializeNoAuth(value,gen,provider);
        }else{
            if(null == value){
                gen.writeString("");
            }else{
                serializeNotNull(value,gen,provider);
            }
        }
    }

}

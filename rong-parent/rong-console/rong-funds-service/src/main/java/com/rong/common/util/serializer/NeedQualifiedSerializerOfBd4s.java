package com.rong.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rong.common.util.NumberUtil;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * bigdecimal to string,4 scale
 */
public class NeedQualifiedSerializerOfBd4s extends NeedQualifiedSerializer {
    @Override
    protected void serializeNotNull(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        super.serializeNotNull(NumberUtil.getScale((BigDecimal)value,4), gen, provider);
    }
}

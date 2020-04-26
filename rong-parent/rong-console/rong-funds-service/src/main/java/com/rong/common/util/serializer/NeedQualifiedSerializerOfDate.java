package com.rong.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rong.common.util.DateUtil;

import java.io.IOException;
import java.util.Date;

/**
 * date to string with '%'
 */
public class NeedQualifiedSerializerOfDate extends NeedQualifiedSerializer {
    @Override
    protected void serializeNotNull(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        super.serializeNotNull(DateUtil.dateToDateString((Date)value,DateUtil.yyyy_MM_dd_EN), gen, provider);
    }
}

package com.rong.assembly.api.module.request;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class TqAppVersionInfo extends TqBase {
    @ApiParam(value = "类型，(ANDROID:安卓，IOS：苹果)",allowableValues = "ANDROID,IOS",required = true)
    @RequireValidator
    private String type;
}

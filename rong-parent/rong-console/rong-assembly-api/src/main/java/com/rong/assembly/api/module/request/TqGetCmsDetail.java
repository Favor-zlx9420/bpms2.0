package com.rong.assembly.api.module.request;

import com.rong.common.annotation.LongRangeValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author lether
 *
 */
@Data
public class TqGetCmsDetail extends TqBase {
    @RequireValidator()
    @LongRangeValidator(min = 1,max = Integer.MAX_VALUE)
    @ApiModelProperty(value = "通过列表获取的新闻id")
    private Long id;
}
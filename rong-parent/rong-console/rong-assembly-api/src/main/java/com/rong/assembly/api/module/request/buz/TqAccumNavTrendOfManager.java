package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import com.rong.common.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TqAccumNavTrendOfManager extends TqBase {

    @ApiModelProperty(value = "基金经理id", name = "personId", required = true)
    @RequireValidator
    private Long personId;

    @DateTimeFormat(pattern = DateUtil.yyyy_MM_dd_EN)
    @ApiModelProperty(value = "开始时间", name = "startDate")
    private Date startDate;

    @DateTimeFormat(pattern = DateUtil.yyyy_MM_dd_EN)
    @ApiModelProperty(value = "结束时间", name = "endDate")
    private Date endDate;
}

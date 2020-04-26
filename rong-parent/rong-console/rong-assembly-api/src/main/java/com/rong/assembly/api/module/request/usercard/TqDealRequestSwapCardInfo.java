package com.rong.assembly.api.module.request.usercard;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqDealRequestSwapCardInfo extends TqUserAuthBase {
    @ApiModelProperty(value = "请求交换名片的记录id，通过请求列表里获取",required = true)
    private Long id;
    @ApiModelProperty("申请人名片id")
    private Long applicantCardInfoId;
    @ApiModelProperty("目标名片（被申请人名片）名片id")
    private Long targetCardInfoId;
    @ApiModelProperty(value = "处理结果（1：同意，2，拒绝）",required = true,allowableValues = "1,2")
    @RequireValidator
    private Integer dealResult;
}

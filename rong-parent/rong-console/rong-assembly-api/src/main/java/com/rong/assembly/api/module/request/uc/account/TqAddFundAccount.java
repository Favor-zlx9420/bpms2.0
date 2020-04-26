package com.rong.assembly.api.module.request.uc.account;

import com.rong.common.annotation.BigDecimalRangeValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增一笔账户
 */
@Data
public class TqAddFundAccount extends TqUserAuthBase {

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id",required = true)
    @RequireValidator
    private Long securityId;

    /**
     * 购买时日期
     */
    @ApiModelProperty(value = "购买时日期，格式为yyyy-MM-dd",required = true)
    private String buyDateStr;

    /**
     * 本金（单位：元）
     */
    @ApiModelProperty(value = "本金（单位：元）",required = true)
    @RequireValidator
    @BigDecimalRangeValidator(min=1,max = 99999999999d)
    private BigDecimal principal;
}

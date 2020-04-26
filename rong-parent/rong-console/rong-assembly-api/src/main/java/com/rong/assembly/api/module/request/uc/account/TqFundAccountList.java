package com.rong.assembly.api.module.request.uc.account;

import com.rong.common.annotation.RegexValidator;
import com.rong.common.module.TqUserAuthPageListBase;
import com.rong.common.util.Validator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 账户列表
 */
@Data
public class TqFundAccountList extends TqUserAuthPageListBase {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("是否删除")
    private Boolean deltag;
    @ApiModelProperty("产品名称,模糊查询")
    private String secFullName;
    @ApiModelProperty("起始购买时间,时间格式应为：yyyyh-MM-dd HH:mm:ss")
    @RegexValidator(regexStr = Validator.DATE_TIME_REG,errorContent = "日期格式应为：yyyyh-MM-dd HH:mm:ss")
    private String beginBuyDate;
    @ApiModelProperty("截止购买时间,时间格式应为：yyyyh-MM-dd HH:mm:ss")
    @RegexValidator(regexStr = Validator.DATE_TIME_REG,errorContent = "日期格式应为：yyyyh-MM-dd HH:mm:ss")
    private String endBuyDate;
    @ApiModelProperty("起始购买份数")
    private BigDecimal beginShare;
    @ApiModelProperty("截止购买份数")
    private String endBuyShare;
    @ApiModelProperty("起始购买本金")
    private BigDecimal beginPrincipal;
    @ApiModelProperty("截止购买本金")
    private String endBuyPrincipal;
}

package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-17 10:08
 **/
@Data
public class TvPfundMktHisNav {

    @ApiModelProperty(value = "证券内部编码")
    private Long securityId;

    @ApiModelProperty(value = "产品名称(简称)")
    private String secShortName;

    @ApiModelProperty(value = "交易日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tradeDate;

    @ApiModelProperty(value = "收盘价")
    private BigDecimal closeIndex;

    @ApiModelProperty(value = "收盘价变动")
    private String closeIndexChange;
}

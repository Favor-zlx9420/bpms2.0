package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
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
public class TvHisNav {

    @ApiModelProperty(value = "净值日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ApiModelProperty(value = "单位净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class)
    private BigDecimal nav;

    @ApiModelProperty(value = "累计净值(分红再投资)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class)
    private BigDecimal adjNav;

    @ApiModelProperty(value = "累计净值(分红不投资)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class)
    private BigDecimal accumNav;

    @ApiModelProperty(value = "累计净值变动(分红再投资)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String adjNavChange;

    @ApiModelProperty(value = "累计净值变动(分红不投资)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String accumNavChange;

    @ApiModelProperty(value = "净值变动")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate;

    @ApiModelProperty(value = "分红")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal dividend;

    @ApiModelProperty(value = "拆分")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal split;
}

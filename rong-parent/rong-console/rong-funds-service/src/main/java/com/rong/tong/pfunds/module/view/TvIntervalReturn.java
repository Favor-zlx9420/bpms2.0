package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-17 10:08
 **/
@Data
public class TvIntervalReturn {

    @ApiModelProperty(value = "日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String endDate;

    @ApiModelProperty(value = "成立以来")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRateEst;

    @ApiModelProperty(value = "今年以来")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRateYtd;

    @ApiModelProperty(value = "近一个月")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate1m;

    @ApiModelProperty(value = "近三个月")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate3m;

    @ApiModelProperty(value = "最近半年")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate6m;

    @ApiModelProperty(value = "最近一年")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate1y;

    @ApiModelProperty(value = "最近两年")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate2y;

    @ApiModelProperty(value = "最年三年")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate3y;

    @ApiModelProperty(value = "最近五年")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate5y;
}

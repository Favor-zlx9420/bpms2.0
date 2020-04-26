package com.rong.user.module.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd2s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfDate;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.user.module.entity.TbUserFundAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TvUserFundAccount extends TbUserFundAccount {
    @ApiModelProperty("产品简称")
    private String secShortName;
    @ApiModelProperty("产品全称")
    private String secFullName;
    @ApiModelProperty("最新净值，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class)
    private BigDecimal nav;
    @ApiModelProperty("最新净值日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfDate.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private Date navDate;
    @ApiModelProperty("参考收益，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd2s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal profit;
    @ApiModelProperty("参考市值，单位：元")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd2s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal markValue;

    @JsonIgnore
    @ApiModelProperty(value = "公募净值",hidden = true)
    private BigDecimal priNav;

    @JsonIgnore
    @ApiModelProperty(value = "私募净值",hidden = true)
    private BigDecimal raisedNav;

    public BigDecimal getNav() {
        if(null != priNav){
            return priNav;
        }
        return raisedNav;
    }

    public BigDecimal getProfit() {
        BigDecimal _cn = getNav();
        BigDecimal _tn = getThenNav();
        BigDecimal _share = getShare();
        if(null != _cn && null != _tn && null != _share){
            return _cn.subtract(_tn).multiply(_share);
        }
        return null;
    }

    public BigDecimal getMarkValue() {
        BigDecimal _cn = getNav();
        BigDecimal _prin = getShare();
        if(null != _cn && null != _prin){
            return _cn.multiply(_prin);
        }
        return null;
    }
}
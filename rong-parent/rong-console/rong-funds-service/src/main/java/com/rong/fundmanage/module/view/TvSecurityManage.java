package com.rong.fundmanage.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.fundmanage.module.entity.TbSecurityManage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TvSecurityManage extends TbSecurityManage {

    @ApiModelProperty("最新净值,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal nav;
    @ApiModelProperty("累计净值,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal accumNav;
    @ApiModelProperty("累计%,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnAccum;
    @ApiModelProperty("年化%,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnA;

    @ApiModelProperty("是否代表作")
    private Boolean isResp;

    @ApiModelProperty("运行时长,单位:天")
    private Integer listDays;

    @ApiModelProperty("产品全名")
    private String secFullName;
    @ApiModelProperty("产品简称")
    private String secShortName;
    @ApiModelProperty("上市日期")
    private Date establishDate;
    private Date expireDate;
    private String scaleInitial;
    private String riskRating;
    private String duration;
    private String partyShortName;
    private String managerName;
    private String investStrategy;
    private BigDecimal applyFee;
    private BigDecimal redeemFee;

    @ApiModelProperty("分级名称")
    private String className;

    public String getSecShortName() {
        if(StringUtil.isNotEmpty(this.getClassName())){
            return secShortName + this.getClassName();
        }
        return secShortName;
    }

    public String getSecFullName() {
        if(StringUtil.isNotEmpty(this.getClassName())){
            return secFullName + this.getClassName();
        }
        return secFullName;
    }

    public String getDuration() {
        if(null != expireDate){
            try {
                return DateUtil.difference(expireDate, new Date()) / 30 + "";
            }catch (Exception e){}
        }
        return duration;
    }
}
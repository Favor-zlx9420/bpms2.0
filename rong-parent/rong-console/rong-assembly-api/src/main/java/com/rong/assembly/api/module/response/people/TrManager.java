package com.rong.assembly.api.module.response.people;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.assembly.api.module.response.org.TrRespOrg;
import com.rong.assembly.api.module.response.product.TrFunds;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.serializer.NeedQualifiedSerializer;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfDate;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Data
public class TrManager {
    @ApiModelProperty("用户类型：假如绑定了系统用户的话")
    private Integer type;
    @ApiModelProperty("用户id：假如绑定了系统用户的话")
    private Long userId;
    @ApiModelProperty("人物id")
    private Long personId;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("职位")
    private String position;
    @ApiModelProperty("头像地址")
    private String headPortrait;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("最高学历")
    private String education;
    @ApiModelProperty("背景、职业背景")
    private String backgroundDesc;
    @ApiModelProperty("背景、职业背景")
    private String backgroundValue;
    @ApiModelProperty("基本信息,介绍")
    private String profile;
    @ApiModelProperty("在线状态（0:未在线，1：在线）")
    private Integer onlineState;
    @ApiModelProperty("代表产品id")
    private Long repSecurityId;
    @ApiModelProperty("代表产品全称")
    private String repSecurityFullName;
    @ApiModelProperty("代表产品简称")
    private String repSecurityShortName;
    @ApiModelProperty("代表产品累计收益,只有私募基金才有")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal repSecReturnAccum;
    @ApiModelProperty("代表产品上市日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfDate.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private Date repEstablishDate;
    @ApiModelProperty("代表产品运行时长,单位:天")
    @JsonSerialize(using = NeedQualifiedSerializer.class)
    private Integer repListDays;
    @ApiModelProperty("代表产品累计净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal repSecAccumNav;

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
    @ApiModelProperty("近一年收益%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnOfLatestYear;
    @ApiModelProperty("近一年风险%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal riskOfLatestYear;
    @ApiModelProperty("从业年限")
    private String workYear;
    @ApiModelProperty("旗下基金总数量")
    @JsonSerialize(using = NeedQualifiedSerializer.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private int fundCounts;
    @ApiModelProperty("旗下私募基金数量")
    @JsonSerialize(using = NeedQualifiedSerializer.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private int priFundCounts;
    @ApiModelProperty("旗下公募基金数量")
    @JsonSerialize(using = NeedQualifiedSerializer.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private int raisedFundCounts;

    @ApiModelProperty("今年以来年化%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnOfThisYear;

    @ApiModelProperty("今年以来年化%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnRateYtd;

    @ApiModelProperty("服务机构")
    private List<TrRespOrg> serviceOrgs;
    @ApiModelProperty("管理私募基金")
    private List<TrFunds> priFunds;
    @ApiModelProperty("管理公募基金")
    private List<TrFunds> raisedFunds;

    @ApiModelProperty(value = "是否被收藏/关注")
    private boolean fav = false;


    @JsonIgnore
    @ApiModelProperty(value = "私募今年以来收益",hidden = true)
    private BigDecimal priReturnOfThisYear;
    @JsonIgnore
    @ApiModelProperty(value = "公募今年以来收益",hidden = true)
    private BigDecimal raisedReturnOfThisYear;

    @ApiModelProperty(value = "私募净值",hidden = true)
    @JsonIgnore
    private BigDecimal priNav;
    @ApiModelProperty(value = "公募净值",hidden = true)
    @JsonIgnore
    private BigDecimal raisedNav;
    @ApiModelProperty(value = "私募累计净值",hidden = true)
    @JsonIgnore
    private BigDecimal priAccumNav;
    @ApiModelProperty(value = "公募累计净值",hidden = true)
    @JsonIgnore
    private BigDecimal raisedAccumNav;

    public int getFundCounts() {
        return priFundCounts + raisedFundCounts;
    }

    public BigDecimal getNav() {
        if(priNav == null){
            return raisedNav;
        }else if(raisedNav == null){
            return priNav;
        }
        return priNav.add(raisedNav).divide(new BigDecimal("2"),4, RoundingMode.HALF_UP);
    }

    public BigDecimal getAccumNav() {
        if(priAccumNav == null){
            return raisedAccumNav;
        }else if(raisedAccumNav == null){
            return priAccumNav;
        }
        return priAccumNav.add(raisedAccumNav).divide(new BigDecimal("2"),4, RoundingMode.HALF_UP);
    }

    public BigDecimal getReturnOfThisYear() {
        if(priReturnOfThisYear == null){
            return raisedReturnOfThisYear;
        }else if(raisedReturnOfThisYear == null){
            return priReturnOfThisYear;
        }
        return priReturnOfThisYear.add(raisedReturnOfThisYear).divide(new BigDecimal("2"),4, RoundingMode.HALF_UP);
    }

    public BigDecimal getReturnRateYtd() {
        return getReturnOfThisYear();
    }

    public String getBackgroundDesc() {
        if(StringUtil.isNotEmpty(backgroundValue)){
            return backgroundValue;
        }
        return backgroundDesc;
    }

    public String getBackgroundValue() {
        if(StringUtil.isNotEmpty(backgroundValue)){
            return backgroundValue;
        }
        return backgroundDesc;
    }

    public Integer getRepListDays() {
        try {
            return DateUtil.difference(repEstablishDate,new Date());
        }catch (Exception e){
            return null;
        }
    }
}

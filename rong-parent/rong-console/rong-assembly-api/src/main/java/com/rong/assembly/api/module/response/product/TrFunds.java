package com.rong.assembly.api.module.response.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.assembly.api.module.response.people.TrManager;
import com.rong.common.util.DateUtil;
import com.rong.common.util.StringUtil;
import com.rong.common.util.serializer.NeedQualifiedSerializer;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfDate;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.store.module.view.TvDirectStoreUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class TrFunds {


    /**
     * （0私募，1信托，2公募）
     */
    @ApiModelProperty("（0私募，1信托，2公募）")
    private Integer type;
    @ApiModelProperty("是否推荐")
    private Boolean recommend;
    @ApiModelProperty("是否热门")
    private Boolean hotSearch;

    @ApiModelProperty("产品id")
    private Long securityId;
    @ApiModelProperty("产品全名")
    private String secFullName;
    @ApiModelProperty("产品简称")
    private String secShortName;
    @ApiModelProperty("分级名称")
    private String className;
    @ApiModelProperty("投资策略")
    private String investStrategy;

    @ApiModelProperty("机构简称")
    private String partyShortName;
    @ApiModelProperty("机构全称")
    private String partyFullName;
    @ApiModelProperty("机构id")
    private Long partyId;

    @ApiModelProperty("累计净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class)
    private BigDecimal accumNav;
    @ApiModelProperty("最新净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class)
    private BigDecimal nav;
    @ApiModelProperty("最新净值日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfDate.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private Date navDate;
    @ApiModelProperty("年化收益，可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnA;
    @ApiModelProperty("累计年化收益，可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnAccum;
    @ApiModelProperty("今年以来年化%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnOfThisYear;
    @ApiModelProperty("今年以来年化%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnRateYtd;
    @ApiModelProperty("近一个月收益%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnRate1m;
    @ApiModelProperty("累计净值(分红再投资)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal adjNav;
    @ApiModelProperty("调整净值回报%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnRate;

    @ApiModelProperty("初始规模，可排序")
    private Long scaleInitial;

    @ApiModelProperty("是否代表作")
    private Boolean isResp;

    @ApiModelProperty("上市日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfDate.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private Date establishDate;
    @ApiModelProperty("运行时长,单位:天")
    @JsonSerialize(using = NeedQualifiedSerializer.class)
    private Integer listDays;

    @ApiModelProperty("近一年收益%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnOfLatestYear;
    @ApiModelProperty("近一年收益%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnRate1y;
    @ApiModelProperty("近一年风险%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal riskOfLatestYear;

    @ApiModelProperty("驻店客服列表")
    List<TvDirectStoreUser> customerServers;
    @ApiModelProperty("基金经理列表")
    List<TrManager> managers;

    @ApiModelProperty("近一年收益评级")
    private String incomeRating;
    @ApiModelProperty("近一年收益评级")
    private String riskRating;


    @ApiModelProperty(value = "是否被收藏/关注")
    private boolean fav = false;

    @ApiModelProperty(value = "产品类型")
    private String produceType;

    @ApiModelProperty(value = "基金状态")
    private String status;
    private String priStatus;
    private String raiseStatus;
    private String priInvestStrategy;
    private String raiseInvestStrategy;

    public String getInvestStrategy() {
        if(StringUtil.isNotEmpty(priInvestStrategy)){
            return priInvestStrategy;
        }else if(StringUtil.isNotEmpty(raiseInvestStrategy)){
            return raiseInvestStrategy;
        }
        return investStrategy;
    }

    public String getStatus() {
        if(StringUtil.isNotEmpty(priStatus)){
            return priStatus;
        }else if(StringUtil.isNotEmpty(raiseStatus)){
            return raiseStatus;
        }
        return status;
    }

    public Integer getListDays() {
        try {
            return DateUtil.difference(establishDate,new Date());
        }catch (Exception e){
            return null;
        }
    }

    public String getSecFullName() {
        if(StringUtil.isNotEmpty(this.getClassName())){
            return secFullName + this.getClassName();
        }
        return secFullName;
    }

    public String getSecShortName() {
        if(StringUtil.isNotEmpty(this.getClassName())){
            return secShortName + this.getClassName();
        }
        return secShortName;
    }

    public BigDecimal getReturnOfThisYear() {
        if(null != returnOfThisYear){
            return returnOfThisYear;
        }
        return returnRateYtd;
    }

    public BigDecimal getReturnRateYtd() {
        if(null != returnRateYtd){
            return returnRateYtd;
        }
        return returnOfThisYear;
    }

    public BigDecimal getReturnOfLatestYear() {
        if(null != returnOfLatestYear){
            return returnOfLatestYear;
        }
        return returnRate1y;
    }

    public BigDecimal getReturnRate1y() {
        if(null != returnRate1y){
            return returnRate1y;
        }
        return returnOfLatestYear;
    }

    public String getIncomeRating() {
        BigDecimal _rate1y = getReturnRate1y();
        if(null != _rate1y){
            double _r = _rate1y.doubleValue();
            if(_r > 50){
                return "优";
            }else if(_r > 25){
                return "良";
            }else if(_r > 0){
                return "中";
            }else{
                return "一般";
            }
        }
        return incomeRating;
    }

    public String getRiskRating() {
        if(null != riskOfLatestYear){
            double _r = riskOfLatestYear.doubleValue();
            if(_r > 40){
                return "优";
            }else if(_r > 30){
                return "良";
            }else if(_r > 15){
                return "中";
            }else if(_r > 0){
                return "一般";
            }
        }
        return riskRating;
    }
}

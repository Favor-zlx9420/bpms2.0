package com.rong.user.module.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.user.module.entity.TbUserOrgFav;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class TvUserOrgFav extends TbUserOrgFav {

    @ApiModelProperty("收藏机构的用户名")
    private String userName;
    @ApiModelProperty("收藏机构的用户真实姓名")
    private String realName;

    @ApiModelProperty("机构简称")
    private String partyShortName;
    @ApiModelProperty("机构全称")
    private String partyFullName;

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

    @ApiModelProperty("今年以来年化%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnOfThisYear;
    /**
     * 私募
     */
    @JsonIgnore
    private BigDecimal returnOfThisYear0;
    /**
     * 公募
     */
    @JsonIgnore
    private BigDecimal returnOfThisYear1;

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
        if(returnOfThisYear0 == null){
            return returnOfThisYear1;
        }else if(returnOfThisYear1 == null){
            return returnOfThisYear0;
        }
        return returnOfThisYear0.add(returnOfThisYear1).divide(new BigDecimal("2"),4, RoundingMode.HALF_UP);
    }
}
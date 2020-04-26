package com.rong.user.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.StringUtil;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.user.module.entity.TbUserProFav;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("用户产品收藏")
public class TvUserProFav extends TbUserProFav {

    @ApiModelProperty("收藏产品的用户名")
    private String userName;
    @ApiModelProperty("收藏产品的用户真实姓名")
    private String realName;

    @ApiModelProperty("产品全名")
    private String secFullName;
    @ApiModelProperty("产品简称")
    private String secShortName;
    @ApiModelProperty("分级名称")
    private String className;

    @ApiModelProperty("投资策略")
    private String investStrategy;
    @ApiModelProperty("最新净值时间")
    private Date navDate;
    @ApiModelProperty("最新净值,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal nav;
    @ApiModelProperty("累计净值,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal accumNav;
    @ApiModelProperty("累计净值(分红再投资)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd4s.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal adjNav;
    @ApiModelProperty("调整净值回报%")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnRate;
    @ApiModelProperty("累计%,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnAccum;
    @ApiModelProperty("年化%,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal returnA;
    @ApiModelProperty("近一个月%,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal last1MonReturnA;

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
}
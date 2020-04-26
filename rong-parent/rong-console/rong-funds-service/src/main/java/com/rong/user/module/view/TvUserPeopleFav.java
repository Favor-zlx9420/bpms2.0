package com.rong.user.module.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.user.module.entity.TbUserPeopleFav;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TvUserPeopleFav extends TbUserPeopleFav {

    @ApiModelProperty("收藏基金经理的用户名")
    private String userName;
    @ApiModelProperty("收藏基金经理的用户真实姓名")
    private String realName;

    @ApiModelProperty("基金经理名字")
    private String name;
    @ApiModelProperty("最高学历")
    private String education;
    @ApiModelProperty("背景/人物介绍")
    private String backgroundDesc;
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
    @ApiModelProperty("代表产品id")
    private String repSecurityId;
    @ApiModelProperty("代表产品全称")
    private String repSecurityFullName;
    @ApiModelProperty("所在公司简称,以'、'隔开")
    private String companyNames;
    @ApiModelProperty("所在公司职位,以'、'隔开")
    private String companyPosts;
    @ApiModelProperty("从业年限")
    private String workYear;
    @ApiModelProperty("旗下基金数量")
    private int fundCounts;

    @ApiModelProperty(value = "旗下私募基金数量",hidden = true)
    @JsonIgnore
    private int fundCounts0;
    @ApiModelProperty(value = "旗下公募基金数量",hidden = true)
    @JsonIgnore
    private int fundCounts1;

    public int getFundCounts() {
        return fundCounts0 + fundCounts1;
    }
}
package com.rong.user.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.user.module.entity.TbUserPeopleReservation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TvUserPeopleReservation extends TbUserPeopleReservation {

    @ApiModelProperty("预约基金经理的用户名")
    private String userName;
    @ApiModelProperty("预约基金经理的用户真实姓名")
    private String realName;

    @ApiModelProperty("机构人员id")
    private Long personId;
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("最高学历")
    private String education;
    @ApiModelProperty("职业背景")
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
    @ApiModelProperty("代表产品")
    private String repPro;
    @ApiModelProperty("所在公司简称,以'、'隔开")
    private String companyNames;
    @ApiModelProperty("所在公司职位,以'、'隔开")
    private String companyPosts;
    @ApiModelProperty("从业年限")
    private String workYear;
    @ApiModelProperty("旗下基金")
    private int fundCounts;
}
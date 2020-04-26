package com.rong.user.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd4s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.user.module.entity.TbUserProReservation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TvUserProReservation extends TbUserProReservation {

    @ApiModelProperty("预约产品的用户名")
    private String userName;
    @ApiModelProperty("预约产品的用户真实姓名")
    private String realName;

    @ApiModelProperty("产品简称")
    private String secShortName;
    @ApiModelProperty("投资策略")
    private String investStrategy;
    @ApiModelProperty("最新净值时间")
    private Date navDate;
    @ApiModelProperty("近一个月%,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private String last1MonReturnA;
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
}
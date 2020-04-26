package com.rong.fundmanage.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.fundmanage.module.entity.TbOrgManage;
import com.rong.member.module.entity.TbMemBase;
import com.rong.tong.pfunds.module.view.TvMdPeople;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class TvOrgManage extends TbOrgManage {
    @ApiModelProperty("机构简称")
    private String partyShortName;
    @ApiModelProperty("机构全称")
    private String partyFullName;
    @ApiModelProperty("旗下基金经理列表")
    List<TvMdPeople> managers;
    @ApiModelProperty("近一年收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal returnOfLatestYear;
    @ApiModelProperty("累计收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal returnAccum;
    @ApiModelProperty("年化收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class)
    private BigDecimal returnA;
    @ApiModelProperty("成立日期")
    private Date recordDate;
}
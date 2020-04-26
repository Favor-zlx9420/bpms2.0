package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-15 16:17
 **/
@Data
public class TvManagerRanking {

    @ApiModelProperty(value = "序号")
    private Integer no;

    @ApiModelProperty(value = "基金经理id")
    private Integer personId;

    @ApiModelProperty(value = "基金经理")
    private String userName;

    @ApiModelProperty(value = "基金公司")
    private List<TvMdInstitution> partyList;

    @ApiModelProperty(value = "机构内部编码")
    private Integer partyId;

    @ApiModelProperty(value = "基金公司(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "基金公司(简称)")
    private String partyFullName;

    @ApiModelProperty(value = "从业年限")
    private BigDecimal year;

    @ApiModelProperty(value = "职业背景")
    private String background;

    @ApiModelProperty(value = "管理产品总数")
    private Integer numAll;

    @ApiModelProperty(value = "夏普比")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String sharpeRatio;

    @ApiModelProperty(value = "收益率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate;

    @ApiModelProperty(value = "代表产品内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "代表产品(简称)")
    private String secShortName;

    @ApiModelProperty(value = "代表产品(全称)")
    private String secFullName;

    @ApiModelProperty(value = "代表产品最新净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal nav;

    @ApiModelProperty(value = "代表产品最新净值日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String endDate;

    @ApiModelProperty(value = "代表产品累计收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnAccum;
}

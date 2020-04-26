package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-17 10:08
 **/
@Data
public class TvCompanySummary {

    @ApiModelProperty(value = "机构内部id")
    private Integer partyId;

    @ApiModelProperty(value = "公司名称(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "公司名称(全称)")
    private String partyFullName;

    @ApiModelProperty(value = "累计收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnAccum;

    @ApiModelProperty(value = "年化收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnA;

    @ApiModelProperty(value = "核心人物")
    private String keyPerson;

    @ApiModelProperty(value = "代表产品(简称)")
    private String secShortName;

    @ApiModelProperty(value = "代表产品(全称)")
    private String secFullName;

    @ApiModelProperty(value = "代表产品id")
    private Integer securityId;

    @ApiModelProperty(value = "产品数(管理)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private Integer numAll;

    @ApiModelProperty(value = "产品数(存续)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private Integer numDura;

    @ApiModelProperty(value = "成立时间")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String recordDate;

    @ApiModelProperty(value = "所在地区")
    private String regCity;

    @ApiModelProperty(value = "备案编号")
    private String regCd;

    @ApiModelProperty(value = "管理规模")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String scale;

}

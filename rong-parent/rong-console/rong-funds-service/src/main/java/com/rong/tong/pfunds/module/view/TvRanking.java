package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-15 16:17
 **/
@Data
public class TvRanking {

    @ApiModelProperty(value = "序号")
    private Integer no;

    @ApiModelProperty(value = "证券内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "产品名称(简称)")
    private String secShortName;

    @ApiModelProperty(value = "产品名称(全称)")
    private String secFullName;

    @ApiModelProperty(value = "基金公司id")
    private Integer partyId;

    @ApiModelProperty(value = "基金公司(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "基金公司(全称)")
    private String partyFullName;

    @ApiModelProperty(value = "成立时间")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String establishDate;

    @ApiModelProperty(value = "最新净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal nav;

    @ApiModelProperty(value = "最新净值日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String endDate;

    @ApiModelProperty(value = "年化")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnOfEstablish;

    @ApiModelProperty(value = "年化后1列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String one;

    @ApiModelProperty(value = "年化后2列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String two;

    @ApiModelProperty(value = "年化后3列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String three;

    @ApiModelProperty(value = "年化后4列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String four;

    @ApiModelProperty(value = "年化后5列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String five;

    @ApiModelProperty(value = "年化后6列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String six;

    @ApiModelProperty(value = "年化后7列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String seven;

    @ApiModelProperty(value = "年化后8列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String eight;

    @ApiModelProperty(value = "年化后9列")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String nine;

    @ApiModelProperty(value = "是否被收藏/关注")
    private boolean fav = false;
}

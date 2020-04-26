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
 * @Date 2020-01-15 20:31
 **/
@Data
public class TvSearchPfundManager {

    @ApiModelProperty(value = "序号")
    private Integer no;

    @ApiModelProperty(value = "用户id")
    private Integer personId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "基金公司")
    private List<TvMdInstitution> partyList;

    @ApiModelProperty(value = "所在公司机构内部id")
    private Integer partyId;

    @ApiModelProperty(value = "所在公司(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "所在公司(全称)")
    private String partyFullName;

    @ApiModelProperty(value = "产品数(管理)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private Integer numAll;

    @ApiModelProperty(value = "产品数(存续)")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private Integer numDura;

    @ApiModelProperty(value = "从业年限")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private Integer year;

    @ApiModelProperty(value = "累计收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String totalReturn;

    @ApiModelProperty(value = "代表产品证券内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "代表产品(简称)")
    private String secShortName;

    @ApiModelProperty(value = "代表产品(全称)")
    private String secFullName;
}

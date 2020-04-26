package com.rong.store.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBdp;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.fundmanage.module.entity.TbProductLabel;
import com.rong.store.module.entity.TbDirectStoreProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TvDirectStoreProduct extends TbDirectStoreProduct {
    private List<TbProductLabel> productLabels;
    @ApiModelProperty("产品全称")
    private String secFullName;
    @ApiModelProperty("产品简称")
    private String secShortName;
    @ApiModelProperty("机构简称")
    private String partyShortName;
    @ApiModelProperty("机构全称")
    private String partyFullName;
    @ApiModelProperty("机构id")
    private Long partyId;

    @ApiModelProperty("近一年年化收益,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal last1YearReturnA;
    @ApiModelProperty("近一年风险,可排序")
    @JsonSerialize(using = NeedQualifiedSerializerOfBdp.class,nullsUsing = NeedQualifiedSerializerOfNull.class)
    private BigDecimal last1YearRisk;
}
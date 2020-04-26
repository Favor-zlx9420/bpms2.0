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
public class TvRankingSimple {

    @ApiModelProperty(value = "序号")
    private Integer no;

    @ApiModelProperty(value = "证券内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "产品名称(简称)")
    private String secShortName;

    @ApiModelProperty(value = "最新净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal nav;

    @ApiModelProperty(value = "收益率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRate;

    @ApiModelProperty(value = "夏普比率")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String sharpeRatio;
}

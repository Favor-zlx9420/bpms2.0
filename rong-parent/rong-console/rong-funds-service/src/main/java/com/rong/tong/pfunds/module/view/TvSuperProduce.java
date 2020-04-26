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
public class TvSuperProduce {

    @ApiModelProperty(value = "证券内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "产品名称(简称)")
    private String secShortName;

    @ApiModelProperty(value = "产品名称(全称)")
    private String secFullName;

    @ApiModelProperty(value = "基金经理")
    private String userName;

    @ApiModelProperty(value = "基金经理id")
    private String personId;

    @ApiModelProperty(value = "基金策略")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String investStrategy;

    @ApiModelProperty(value = "成立以来收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRateEst;

    @ApiModelProperty(value = "今年以来收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String returnRateYtd;

    @ApiModelProperty(value = "最新净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal nav;

    @ApiModelProperty(value = "最新净值日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String endDate;

    @ApiModelProperty(value = "开放日")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String openDateDesc;

    @ApiModelProperty(value = "成立以来夏普")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String sharpeRatio;

    @ApiModelProperty(value = "成立以来最大回撤")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String maxDrawdown;

    @ApiModelProperty(value = "推荐理由")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String labelReason;

    @ApiModelProperty(value = "标签0")
    private String labelVar0;

    @ApiModelProperty(value = "标签1")
    private String labelVar1;

    @ApiModelProperty(value = "是否被收藏/关注")
    private boolean fav = false;
}

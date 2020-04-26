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
public class TvSearchPfundInfo {

    @ApiModelProperty(value = "序号")
    private Integer no;

    @ApiModelProperty(value = "证券内部编码")
    private Integer securityId;

    @ApiModelProperty(value = "产品名称(简称)")
    private String secShortName;

    @ApiModelProperty(value = "产品名称(全称)")
    private String secFullName;

    @ApiModelProperty(value = "管理人(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "管理人id")
    private String partyId;

    @ApiModelProperty(value = "管理人(全称)")
    private String partyFullName;

    @ApiModelProperty(value = "基金经理")
    private List<TvMdPeople> managers;

    @ApiModelProperty(value = "基金经理")
    private String userName;

    @ApiModelProperty(value = "基金经理id")
    private String personId;

    @ApiModelProperty(value = "成立时间")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String establishDate;

    @ApiModelProperty(value = "最新净值")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private BigDecimal nav;

    @ApiModelProperty(value = "最新净值日期")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String endDate;

    @ApiModelProperty(value = "累计收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String accumNav;

    @ApiModelProperty(value = "年化收益")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String annualTotalReturn;

    @ApiModelProperty(value = "最大回撤")
    @JsonSerialize(using = NeedQualifiedSerializerOfBd.class)
    private String maxDrawdown;

    @ApiModelProperty(value = "是否被收藏/关注")
    private boolean fav = false;

    public void setNavDetail(TvSearchPfundInfo source) {
        this.setNav(source.getNav());
        this.setEndDate(source.getEndDate());
        this.setAccumNav(source.getAccumNav());
        this.setAnnualTotalReturn(source.getAnnualTotalReturn());
        this.setMaxDrawdown(source.getMaxDrawdown());
    }
}

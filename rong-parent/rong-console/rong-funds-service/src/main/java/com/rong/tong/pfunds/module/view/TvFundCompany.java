package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-17 10:08
 **/
@Data
public class TvFundCompany {

    @ApiModelProperty(value = "机构内部id")
    private Integer partyId;

    @ApiModelProperty(value = "公司名称(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "公司名称(全称)")
    private String partyFullName;

    @ApiModelProperty(value = "核心人物")
    private String keyPerson;

    @ApiModelProperty(value = "代表产品(简称)")
    private String secShortName;

    @ApiModelProperty(value = "代表产品(全称)")
    private String secFullName;

    @ApiModelProperty(value = "产品数(管理)")
    private Integer numAll;

    @ApiModelProperty(value = "产品数(存续)")
    private Integer numDura;

    @ApiModelProperty(value = "成立时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordDate;

    @ApiModelProperty(value = "所在地区")
    private String regCity;

    @ApiModelProperty(value = "备案编号")
    private String regCd;

    @ApiModelProperty(value = "管理规模")
    private String scale;

}

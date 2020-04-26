package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-15 19:51
 **/
@Data
public class TvSearchPfundInstInfo {

    @ApiModelProperty(value = "序号")
    private Integer no;

    @ApiModelProperty(value = "机构内部id")
    private Integer partyId;

    @ApiModelProperty(value = "公司名称(简称)")
    private String partyShortName;

    @ApiModelProperty(value = "公司名称(全称)")
    private String partyFullName;

    @ApiModelProperty(value = "核心人物")
    private String keyPerson;

    @ApiModelProperty(value = "产品数(管理)")
    private Integer numAll;

    @ApiModelProperty(value = "产品数(存续)")
    private Integer numDura;

    @ApiModelProperty(value = "成立时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordDate;

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

package com.rong.assembly.api.module.request.usercard;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqCardInfoList extends TqMySimpleList {
    @ApiModelProperty(value = "公司名称")
    private String company;
    @ApiModelProperty(value = "职位")
    private String position;
    @ApiModelProperty(value = "姓")
    private String firstName;
    @ApiModelProperty(value = "名")
    private String lastName;
}

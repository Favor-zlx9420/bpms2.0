package com.rong.tong.pfunds.module.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-17 10:08
 **/
@Data
public class TvPfundMktList {

    @ApiModelProperty(value = "证券内部编码")
    private Long securityId;

    @ApiModelProperty(value = "产品名称(简称)")
    private String secShortName;
}

package com.rong.tong.pfunds.module.request;

import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqNewsDetail extends TqBase {

    @ApiModelProperty(value = "id", name = "id", required = true)
    private Long id;
}
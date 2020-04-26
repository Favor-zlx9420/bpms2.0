package com.rong.assembly.api.module.request;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class TqAdList extends TqPageListBase {
    @ApiParam("类型，1：信息；2：产品")
    private Integer type;
    @ApiModelProperty("广告位置（0：广告位值0，1：广告位置1......）")
    private Integer position;
}

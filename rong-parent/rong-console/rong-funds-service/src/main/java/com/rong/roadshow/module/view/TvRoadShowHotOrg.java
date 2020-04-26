package com.rong.roadshow.module.view;

import com.rong.roadshow.module.entity.TbRoadShowHotOrg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvRoadShowHotOrg extends TbRoadShowHotOrg {
    @ApiModelProperty("机构简称")
    private String partyShortName;
    @ApiModelProperty("机构全称")
    private String partyFullName;
}
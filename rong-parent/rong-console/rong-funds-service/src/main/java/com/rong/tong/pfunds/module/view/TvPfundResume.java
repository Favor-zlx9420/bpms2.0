package com.rong.tong.pfunds.module.view;

import com.rong.tong.pfunds.module.entity.TbPfundResume;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvPfundResume extends TbPfundResume {
    @ApiModelProperty("历任公司简称")
    private String partyShortName;
}
package com.rong.assembly.api.module.request.buz;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqFavUserListOfManager extends TqPageListBase {
    @ApiModelProperty(value = "基金经理id",required = true)
    private Long personId;
}

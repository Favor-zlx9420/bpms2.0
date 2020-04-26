package com.rong.assembly.api.module.request.uc.fav;

import com.rong.common.module.TqUserAuthPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqFavProList extends TqUserAuthPageListBase {
    @ApiModelProperty(value = "类型{0私募，1信托，2公募}")
    private Integer type;
}

package com.rong.assembly.api.module.request.uc.reservation;

import com.rong.common.module.TqUserAuthPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 预约我的
 */
@Data
public class TqReservationList extends TqUserAuthPageListBase {
    @ApiModelProperty(value = "处理状态（0：未处理，1：已处理）")
    private Integer dealStatus;
    @ApiModelProperty(value = "预约类型（0：机构；1：基金经理；2：产品）")
    private Integer type;
}

package com.rong.assembly.api.module.request;

import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-16 15:14
 **/
@Data
public class TqCompanyDetail extends TqBase {

    @ApiModelProperty(value = "基金公司id", name = "partyId", required = true)
    private Integer partyId;
}

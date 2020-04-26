package com.rong.assembly.api.module.request;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author ludexin
 * @Date 2020-01-16 15:14
 **/
@Data
public class TqHisNav extends TqPageListBase {
    @ApiModelProperty(value = "证券内部编码", name = "securityId", required = true)
    private Integer securityId;
}

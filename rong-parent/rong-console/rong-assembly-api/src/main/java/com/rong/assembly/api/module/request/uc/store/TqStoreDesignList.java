package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.module.TqUserAuthPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 直营店装潢列表
 */
@Data
public class TqStoreDesignList extends TqUserAuthPageListBase {
    @ApiModelProperty("0:未审核，1：通过审核，2再次提交，3：未通过审核")
    private Integer auditState;
    @ApiModelProperty("是否可见")
    private Boolean visible;
    @ApiModelProperty("是否删除")
    private Boolean deltag;
}

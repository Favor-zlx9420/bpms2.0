package com.rong.assembly.api.module.request.store;

import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询直营店装潢记录
 */
@Data
public class TqStoreDesign extends TqPageListBase {
    @ApiModelProperty(value = "机构id",required = true)
    private Long partyId;
}

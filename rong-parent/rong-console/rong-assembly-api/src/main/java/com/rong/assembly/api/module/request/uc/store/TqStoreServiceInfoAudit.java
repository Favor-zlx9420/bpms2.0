package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqStoreServiceInfoAudit extends TqUserAuthBase {
    @ApiModelProperty("服务内容id，通过列表获取的id")
    private Long serviceId;
    @ApiModelProperty("管理者审核结果（0：未审核，1：属实，-1：非实）")
    private Integer auditResult;
}

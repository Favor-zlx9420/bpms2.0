package com.rong.assembly.api.module.request.uc;

import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqOrgProxy extends TqUserAuthBase {
    @ApiModelProperty("代理机构名称,模糊查询")
    private String orgName;
}

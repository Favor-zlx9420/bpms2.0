package com.rong.assembly.api.module.request.store;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUserServiceOrg extends TqBase {
    @ApiModelProperty("客户id：跟客服/直营店经理聊天的那个人的userId")
    private Long customerUserId;
    @ApiModelProperty(value = "客服/直营店经理用户id",required = true)
    @RequireValidator
    private Long serviceUserId;
}

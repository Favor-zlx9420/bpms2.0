package com.rong.assembly.api.module.response.usercard;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TrSendSwapInfo {
    @ApiModelProperty("我发送的数量")
    private int sendCount;
    @ApiModelProperty("接收别人发送给我的数量")
    private int receiveCount;
}

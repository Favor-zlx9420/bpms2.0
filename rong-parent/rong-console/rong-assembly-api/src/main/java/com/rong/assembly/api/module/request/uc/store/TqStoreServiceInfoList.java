package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.module.TqUserAuthPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqStoreServiceInfoList extends TqUserAuthPageListBase {
    @ApiModelProperty("客服userId,客服查自己的不需要带，主管查哪个客服的需要带")
    private Long customerUserId;
    @ApiModelProperty("管理者审核结果（0：未审核，1：属实，-1：非实）")
    private Integer auditResult;
}

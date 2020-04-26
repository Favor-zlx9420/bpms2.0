package com.rong.assembly.api.module.request.uc.account;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 删除/恢复账户记录
 */
@Data
public class TqDelOrRecFundAccounts extends TqUserAuthBase {
    /**
     * ids
     */
    @ApiModelProperty(value = "账户记录id列表",required = true)
    @RequireValidator
    private List<Long> ids;
}

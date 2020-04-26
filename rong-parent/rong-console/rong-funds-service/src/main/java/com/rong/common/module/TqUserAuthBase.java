package com.rong.common.module;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * creator : whh-lether
 * date    : 2018/12/19 17:37
 * desc    :
 **/
@Data
public class TqUserAuthBase extends TqBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private Long userId;

    public Long getUserId() {
        return getLoginUserId();
    }
}

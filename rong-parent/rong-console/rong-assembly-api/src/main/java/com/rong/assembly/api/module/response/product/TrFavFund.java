package com.rong.assembly.api.module.response.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户收藏的产品信息
 */
@Data
public class TrFavFund extends TrFunds {
    @ApiModelProperty("收藏产品的用户名")
    private String userName;
    @ApiModelProperty("收藏产品的用户真实姓名")
    private String realName;
}

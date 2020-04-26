package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 申请金色vip
 */
@Data
public class TqAppVip1 extends TqUserAuthBase {

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 资料认证1
     */
    @ApiModelProperty(value = "资料认证1",required = true)
    @RequireValidator
    private String certificate1Url;

    /**
     * 资料认证2
     */
    @ApiModelProperty("资料认证2")
    private String certificate2Url;

    /**
     * 资料认证3
     */
    @ApiModelProperty("资料认证3")
    private String certificate3Url;

    /**
     * 资料认证4
     */
    @ApiModelProperty("资料认证4")
    private String certificate4Url;

    /**
     * 资料认证5
     */
    @ApiModelProperty("资料认证5")
    private String certificate5Url;

    /**
     * 资料认证6
     */
    @ApiModelProperty("资料认证6")
    private String certificate6Url;

    /**
     * 资料认证7
     */
    @ApiModelProperty("资料认证7")
    private String certificate7Url;

    /**
     * 资料认证8
     */
    @ApiModelProperty("资料认证8")
    private String certificate8Url;
}

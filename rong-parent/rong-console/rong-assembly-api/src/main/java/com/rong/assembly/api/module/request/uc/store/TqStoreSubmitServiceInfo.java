package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqStoreSubmitServiceInfo extends TqUserAuthBase {

    /**
     * 被服务用户id
     */
    @ApiModelProperty(value = "被服务用户id",required = true)
    @RequireValidator
    private Long investorUserId;

    /**
     * 服务内容
     */
    @ApiModelProperty(value = "服务内容",required = true)
    @RequireValidator
    private String content;

    /**
     * 服务内容关联url
     */
    @ApiModelProperty("服务内容关联url")
    private String linkUrl;

    /**
     * 服务内容图片截图url
     */
    @ApiModelProperty("服务内容图片截图url")
    private String picUrl;

    @ApiModelProperty("类型0：产品，1：机构")
    private Integer type = 0;
}

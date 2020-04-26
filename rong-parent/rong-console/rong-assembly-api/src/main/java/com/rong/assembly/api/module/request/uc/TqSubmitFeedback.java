package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqSubmitFeedback extends TqUserAuthBase {

    /**
     * 反馈标题
     */
    @ApiModelProperty(value = "反馈标题")
    private String title;

    /**
     * 反馈内容
     */
    @ApiModelProperty(value = "反馈内容",required = true)
    @RequireValidator
    private String content;

    /**
     * 相关连接
     */
    @ApiModelProperty("相关连接")
    private String link;
}

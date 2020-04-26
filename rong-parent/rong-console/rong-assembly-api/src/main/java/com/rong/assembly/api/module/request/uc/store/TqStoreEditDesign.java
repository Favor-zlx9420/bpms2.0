package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增一条装潢记录
 */
@Data
public class TqStoreEditDesign extends TqUserAuthBase {
    /**
     * id
     */
    @ApiModelProperty(value = "装潢店id",required = true)
    @RequireValidator
    private Long id;

    /**
     * 是否可见
     */
    @ApiModelProperty(value = "是否可见",required = true)
    @RequireValidator
    private Boolean visible;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题",required = true)
    @RequireValidator
    private String title;

    /**
     * 副标题
     */
    @ApiModelProperty(value = "副标题")
    private String subTitle;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容",required = true)
    @RequireValidator
    private String content;

    /**
     * 排序，越小在越前面
     */
    @ApiModelProperty("排序，越小在越前面")
    private BigDecimal sort;
}

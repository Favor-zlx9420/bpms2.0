package com.rong.assembly.api.module.request.uc.store;

import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增一条装潢记录
 */
@Data
public class TqStoreAddDesign extends TqUserAuthBase {
    /**
     * 是否可见
     */
    @ApiModelProperty("是否可见")
    private Boolean visible;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 副标题
     */
    @ApiModelProperty(value = "副标题")
    private String subTitle;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 排序，越小在越前面
     */
    @ApiModelProperty("排序，越小在越前面")
    private BigDecimal sort;
}

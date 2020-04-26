package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RegexValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import com.rong.common.util.Validator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUploadRoadShow extends TqUserAuthBase {
    @ApiModelProperty(value = "路演分类")
    //@RequireValidator
    private Long id;
    @ApiModelProperty(value = "路演分类")
    //@RequireValidator
    private Long cateId;
    @ApiModelProperty("路演标签")
    @RequireValidator
    private Long labelId;
    @ApiModelProperty(value = "标签id0")
    private Long labelId0;
    @ApiModelProperty(value = "路演时间",required = true)
    @RequireValidator
    @RegexValidator(regexStr = Validator.DATE_TIME_REG,errorContent = "只允许提交'yyyy-MM-dd HH:mm:ss'格式的时间")
    private String showDate;
    @ApiModelProperty(value = "路演时长（单位：秒）",required = true)
    @RequireValidator
    private Long showDuration;
    @ApiModelProperty(value = "主讲人",required = true)
    @RequireValidator
    private String presenter;
    @ApiModelProperty(value = "主讲人介绍",required = true)
    @RequireValidator
    private String presenterIntroduce;
    @ApiModelProperty(value = "路演标题",required = true)
    @RequireValidator
    private String title;
    @ApiModelProperty(value = "路演详情",required = true)
    @RequireValidator
    private String detail;

    /**
     * 封面图片
     */
    @ApiModelProperty(value = "封面图片",required = true)
    //@RequireValidator
    private String coverImage;
    /**
     * 路演视频
     */
    @ApiModelProperty(value = "路演视频",required = true)
    //@RequireValidator
    private String video;

    /**
     * 路演文档
     */
    @ApiModelProperty(value = "路演文档")
    //@RequireValidator
    private String doc;

    /**
     * 路演文档2
     */
    @ApiModelProperty(value = "路演文档2")
    //@RequireValidator
    private String doc2;

}

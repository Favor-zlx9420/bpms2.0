package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rong.tong.pfunds.module.entity.TbVnewsContentV1;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TvNewsList {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "发布时间(没有年份)")
    @JsonFormat(pattern="MM-dd")
    private Date publishTimeMd;

    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishTime;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "1为通联数据2为自产数据")
    private Integer type;

    @ApiModelProperty(value = "封面图")
    private String picUrl;
}
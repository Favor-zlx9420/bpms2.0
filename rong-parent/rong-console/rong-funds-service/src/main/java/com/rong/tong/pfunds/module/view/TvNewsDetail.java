package com.rong.tong.pfunds.module.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TvNewsDetail {

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

    @ApiModelProperty(value = "封面图")
    private String picUrl;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "内容")
    private String body;

    @ApiModelProperty(value = "新闻类别")
    private String genre;

    @ApiModelProperty(value = "s3的地址")
    private String s3Url;

    @ApiModelProperty(value = "1为通联数据2为自产数据")
    private Integer type;
}
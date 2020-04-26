package com.rong.roadshow.module.view;

import com.rong.roadshow.module.entity.TbUserRoadShowFav;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TvUserRoadShowFav extends TbUserRoadShowFav {
    @ApiModelProperty("路演标题")
    private String title;
    @ApiModelProperty("路演时间")
    private Date showDate;
    @ApiModelProperty("主讲")
    private String presenter;
    @ApiModelProperty("预约路演示例图")
    private String coverImageUrl;
    @ApiModelProperty("预约用户")
    private String viewUsers;
    @ApiModelProperty(value = "路演时长（单位：秒）")
    private Long showDuration;
    @ApiModelProperty(value = "分类名称")
    private String cateName;
    @ApiModelProperty(value = "标签名称")
    private String labelName;
    @ApiModelProperty(value = "标签名称0")
    private String labelName0;
}
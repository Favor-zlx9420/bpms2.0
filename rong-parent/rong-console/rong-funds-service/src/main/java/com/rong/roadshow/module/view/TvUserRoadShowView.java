package com.rong.roadshow.module.view;

import com.rong.roadshow.module.entity.TbUserRoadShowView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TvUserRoadShowView extends TbUserRoadShowView {
    @ApiModelProperty("路演标题")
    private String title;
    @ApiModelProperty("路演时间")
    private Date showDate;
    @ApiModelProperty("路演结束时间")
    private Date endDate;
    @ApiModelProperty("主讲")
    private String presenter;
    @ApiModelProperty("路演示例图")
    private String coverImageUrl;
    @ApiModelProperty("查看用户数")
    private String viewUsers;
    @ApiModelProperty(value = "路演时长（单位：s）")
    private Long showDuration;

    public Long getShowDuration() {
        if(null != this.getEndDate() && null != this.getShowDate()){
            this.showDuration = (this.getEndDate().getTime() - this.getShowDate().getTime())/1000;
        }
        return showDuration;
    }
}
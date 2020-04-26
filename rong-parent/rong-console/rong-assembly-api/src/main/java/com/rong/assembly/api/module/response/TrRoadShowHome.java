package com.rong.assembly.api.module.response;

import com.rong.roadshow.module.view.TvRoadShowInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("路演首页信息")
public class TrRoadShowHome {
    @ApiModelProperty("在线路演总场数")
    private int onlineCount;
    @ApiModelProperty("路演机构数")
    private int orgCount;
    @ApiModelProperty("推荐路演列表")
    private List<TvRoadShowInfo> tops;
    @ApiModelProperty("预告路演列表")
    private List<TvRoadShowInfo> reservation;
}

package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.SqlCondition;
import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqRoadShowList extends TqPageListBase {
    @ApiModelProperty("路演分类id")
    @SqlCondition("e.cateId.in")
    private Long cateId;
    @ApiModelProperty("路演标签id")
    @SqlCondition("e.labelId.in")
    private Long labelId;
    @ApiModelProperty("关键字搜索")
    @SqlCondition("e.title.like,e.presenter.like")
    private String keyword;
    @ApiModelProperty("搜索开始时间：格式YYYY-MM-dd HH:mm:ss")
    private String beginSearchDate;
    @ApiModelProperty("搜索截止时间：格式YYYY-MM-dd HH:mm:ss")
    private String endSearchDate;
    @ApiModelProperty("路演归属企业")
    @SqlCondition("e.partyId.in")
    private Long partyId;
    @ApiModelProperty("路演嘉宾")
    @SqlCondition("e.presenter.in")
    private String presenter;
}

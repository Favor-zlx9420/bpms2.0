package com.rong.tong.pfunds.module.view;

import com.rong.tong.pfunds.module.entity.TbMdPeople;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvMdPeople extends TbMdPeople {
    private Boolean visible = Boolean.FALSE;
    private Boolean recommend = Boolean.FALSE;
    private Boolean hotSearch = Boolean.FALSE;
    private String userName;
    @ApiModelProperty("用户id：假如绑定了系统用户的话")
    private Long userId;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("职位")
    private String position;
    @ApiModelProperty("头像地址")
    private String headPortrait;
    @ApiModelProperty("昵称")
    private String nickName;
    private Integer type;
    private String educationInfo;

    @ApiModelProperty("从业年限")
    private String workYear0;
    @ApiModelProperty("从业年限")
    private String workYear1;
    @ApiModelProperty("旗下基金数量")
    private int fundCounts;
}
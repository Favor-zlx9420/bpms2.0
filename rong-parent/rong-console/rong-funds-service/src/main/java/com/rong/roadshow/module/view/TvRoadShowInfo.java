package com.rong.roadshow.module.view;

import com.rong.roadshow.module.entity.TbRoadShowInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TvRoadShowInfo extends TbRoadShowInfo {
    @ApiModelProperty(value = "分类名称")
    private String cateName;
    @ApiModelProperty(value = "标签名称")
    private String labelName;
    @ApiModelProperty(value = "标签名称0")
    private String labelName0;
    @ApiModelProperty(value = "上传用户名")
    private String uploadUserName;
    @ApiModelProperty(value = "上传用户名")
    private String uploadUserPhone;
    @ApiModelProperty(value = "上传用户姓名")
    private String uploadUserRealName;
    @ApiModelProperty(value = "路演机构名称")
    private String orgName;
    @ApiModelProperty(value = "路演机构法人代表")
    private String legalRep;
    @ApiModelProperty(value = "路演机构旗下多少场路演")
    private Integer showCount;

    @ApiModelProperty(value = "是否被收藏/关注")
    private boolean fav = false;

    @ApiModelProperty(value = "拒绝时间")
    private Date rejectTime;
    @ApiModelProperty(value = "拒绝原因")
    private String rejectDescription;


}
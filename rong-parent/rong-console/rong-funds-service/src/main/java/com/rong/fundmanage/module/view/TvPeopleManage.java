package com.rong.fundmanage.module.view;

import com.rong.fundmanage.module.entity.TbPeopleManage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvPeopleManage extends TbPeopleManage {
    private String position;
    private String realName;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像地址")
    private String headPortrait;
}
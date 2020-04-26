package com.rong.fundmanage.module.view;

import com.rong.fundmanage.module.entity.TbOrgProxy;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvOrgProxy extends TbOrgProxy {
    @ApiModelProperty("用户登录名")
    private String userName;
    @ApiModelProperty("用户真实姓名")
    private String realName;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户职位")
    private String position;
    @ApiModelProperty("用户头像地址")
    private String headPortrait;
    @ApiModelProperty("代理机构简称")
    private String partyShortName;
    @ApiModelProperty("代理机构全称")
    private String partyFullName;
}
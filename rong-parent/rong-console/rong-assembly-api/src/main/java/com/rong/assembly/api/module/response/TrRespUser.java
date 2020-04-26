package com.rong.assembly.api.module.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class TrRespUser implements Serializable {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("人物id")
    private Long personId;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("用户名")
    private String realName;
    @ApiModelProperty("职位")
    private String position;
    @ApiModelProperty("头像地址")
    private String headPortrait;
    @ApiModelProperty("所服务机构简称")
    private String partyShortName;
    @ApiModelProperty("所服务机构简称")
    private String partyFullName;
}

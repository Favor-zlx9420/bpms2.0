package com.rong.store.module.view;

import com.rong.store.module.entity.TbDirectStoreDesign;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvDirectStoreDesign extends TbDirectStoreDesign {
    @ApiModelProperty("申请用户真实姓名")
    private String appRealName;
    @ApiModelProperty("申请用户用户名")
    private String appUserName;
    @ApiModelProperty("机构简称")
    private String partyShortName;
}
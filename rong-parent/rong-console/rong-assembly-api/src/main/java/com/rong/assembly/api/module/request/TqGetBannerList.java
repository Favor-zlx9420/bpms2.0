package com.rong.assembly.api.module.request;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqGetBannerList extends TqBase {
    @ApiModelProperty(value = "页面类型（0：首页，1：用户中心,5：ANDROID1，6：ANDROID2，7：ANDROID3，8：WAP1，9：WAP2，10：WAP3，11：路演首页）",required = true)
    @RequireValidator
    private Integer pageType;
}

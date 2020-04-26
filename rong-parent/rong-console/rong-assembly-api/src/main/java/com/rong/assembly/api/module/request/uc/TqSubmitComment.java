package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqSubmitComment extends TqUserAuthBase {
    @ApiModelProperty(value = "评论类型（0：机构；1：基金经理；2：产品）",required = true)
    @RequireValidator
    private Integer type;
    @ApiModelProperty(value = "评论类型（评论对象id,机构为partyId，产品为securityId，基金经理为personId）",required = true)
    @RequireValidator
    private Long targetId;
    @ApiModelProperty(value = "评论内容",required = true)
    @RequireValidator
    private String content;
}

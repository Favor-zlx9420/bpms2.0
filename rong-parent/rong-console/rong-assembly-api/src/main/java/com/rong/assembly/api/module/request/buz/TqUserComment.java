package com.rong.assembly.api.module.request.buz;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqPageListBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUserComment extends TqPageListBase {
    @ApiModelProperty(value = "评论类型（0：机构；1：基金经理；2：产品）",required = true)
    @RequireValidator
    private Integer type;
    @ApiModelProperty(value = "评论类型（评论对象id,机构为partyId，产品为securityId，基金经理为personId）",required = true)
    @RequireValidator
    private Long targetId;
}

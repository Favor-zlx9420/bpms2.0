package com.rong.assembly.api.module.request.usercard;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqSwapMessageList extends TqMySimpleList {
    @ApiModelProperty(value = "处理状态（0：未处理，1：已同意，2：已拒绝）",allowableValues = "0,1,2",example = "0")
    private Integer dealResult;
    @ApiModelProperty(value = "对象（0：别人向我发起的请求，1：我向别人发起的请求）")
    private Integer target;
    public enum SwapTarget{
        别人向我发起的请求,我向别人发起的请求
    }
}

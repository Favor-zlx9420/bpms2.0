package com.rong.assembly.api.module.request.usercard;

import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TqUpdateAlbum extends TqUserAuthBase {

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址",required = true)
    private String picUrl;

    /**
     * 图片名称
     */
    @ApiModelProperty("图片名称")
    private String picName;

    /**
     * 说明
     */
    @ApiModelProperty("说明")
    private String remark;
}

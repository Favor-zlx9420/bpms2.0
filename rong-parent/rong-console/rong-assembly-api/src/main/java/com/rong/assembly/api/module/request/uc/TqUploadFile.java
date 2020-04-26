package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TqUploadFile extends TqUserAuthBase {
    /**
     * 上传文件
     */
    @ApiModelProperty(value = "文件名称",required = true)
    @RequireValidator
    private MultipartFile file;
    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型",required = true)
    @RequireValidator
    private Integer type;

}

package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RegexValidator;
import com.rong.common.annotation.RequireValidator;
import com.rong.common.module.TqUserAuthBase;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class TqCheckFile extends TqUserAuthBase {
    @ApiParam("文件md5值")
    @RequireValidator
    private String fileMd5;
    @ApiParam("文件路径")
    @RequireValidator
    @RegexValidator(regexStr = "^([a-z0-9A-Z_]){1,10}$",errorContent = "文件路径只允许数字、字母、下划线组合[1-10位]")
    private String savePath;
    @ApiParam("文件业务类型（30：路演视频）")
    @RequireValidator
    private Integer serviceType;
    @ApiParam("文件扩展名，不需要加 `.` ")
    @RequireValidator
    private String ext;
}

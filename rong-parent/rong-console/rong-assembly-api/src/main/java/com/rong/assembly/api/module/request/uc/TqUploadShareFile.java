package com.rong.assembly.api.module.request.uc;

import com.rong.common.annotation.RequireValidator;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class TqUploadShareFile extends TqCheckFile {
    /**
     * 上传文件
     */
    @ApiModelProperty(value = "分片文件", required = true)
    @RequireValidator
    private MultipartFile file;
    @ApiParam("分片id")
    private String id;
    @ApiParam("文件名称")
    private String name;
    @ApiParam("文件类型")
    private String type;
    @ApiParam("最后一次修改时间")
    private Date lastModifiedDate;
    @ApiParam("分片文件大小")
    private Long size;
    @ApiParam("总分片大小索引，与chunk成对出现")
    private Integer chunks;
    @ApiParam("当前分片索引,为空表示一次性上传")
    private Integer chunk;
}

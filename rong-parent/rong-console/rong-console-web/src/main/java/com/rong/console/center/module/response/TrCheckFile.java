package com.rong.console.center.module.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TrCheckFile {
    /**
     * 已经上传完毕的分片(没有上传完毕才会返回该参数)
     */
    private List hadChunks;
    /**
     * 已经上传完毕的文件地址
     */
    private String fileUrl;
    /**
     * 文件是否已经上传完毕
     */
    private boolean exists;
}

package com.rong.console.center.config;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.JSONUtil;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.entity.TbImageSams;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * creator : whh-lether
 * date    : 2019/8/29 13:58
 * desc    :
 **/
@Component
public class FastDFSClientWrapper {
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    ViyBasicCache viyBasicCache;

    @Autowired
    private DictionaryCache dictionaryCache;

    public TbImageSams uploadFile(String suffix, MultipartFile file, HttpServletRequest request, boolean pushPic) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        TbImageSams newSam=new TbImageSams();
        int type = SysEnumContainer.SysType.PICTORIAL_INFORMATION.getValue();
        if(!CommonUtil.isEqual(request.getParameter("dir"), "image")){
            type = SysEnumContainer.SysType.FILE_CLASS.getValue();
        }
        String fullPath = dictionaryCache.getValue(DictionaryKey.Keys.FILE_SERVER_DOMAIN_NAME.getValue()) + storePath.getFullPath();
        //小图地址
        newSam.setLmtSrc(fullPath);
        newSam.setBigSrc(fullPath);
        newSam.setCntSrc(fullPath);
        newSam.setType(type);
        newSam.setName(request.getParameter("name"));
        if(!CommonUtil.isNull(newSam.getName())){
            //newSam.setName(new String(newSam.getName().getBytes("ISO-8859-1"),"UTF-8"));
        }
        String labelIds = request.getParameter("lbIds");
        if(CommonUtil.isNumOrD(labelIds)){
            newSam.setLabelIds(labelIds);
        }
        if(pushPic){
            viyBasicCache.publishMulToOne(DictionaryKey.ViyCacheSubstrTopic.PICTURE_COLLECTOR.getValue()+"-"+suffix, JSONUtil.toJSONString(newSam));
        }
        newSam.setLmtSrc(newSam.getLmtSrc() + "?v=" + System.currentTimeMillis());
        newSam.setBigSrc(newSam.getBigSrc() + "?v=" + System.currentTimeMillis());
        newSam.setCntSrc(newSam.getCntSrc() + "?v=" + System.currentTimeMillis());
        return newSam;
    }
}

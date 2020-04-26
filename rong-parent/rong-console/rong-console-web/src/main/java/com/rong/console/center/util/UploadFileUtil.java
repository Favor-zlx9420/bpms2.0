package com.rong.console.center.util;

import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.exception.CustomerException;
import com.rong.common.util.CommonUtil;
import com.rong.common.util.DateUtil;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.StringUtil;
import com.rong.console.center.module.request.TqCheckFile;
import com.rong.console.center.module.request.TqUploadShareFile;
import com.rong.console.center.module.response.TrCheckFile;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.entity.TbImageSams;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * creator : whh-lether
 * date    : 2019/3/25 15:54
 * desc    :
 **/
public class UploadFileUtil {
    public static TbImageSams kindEditorUpload(
                                       String subff,
                                       DictionaryCache dictionaryCache,
                                       ViyBasicCache viyBasicCache,
                                       MultipartFile imgFile,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       String dirName)throws Exception{
        return kindEditorUploadOfName(subff,dictionaryCache,viyBasicCache,imgFile,request,response,dirName,
                DateUtil.getCurDateTime(DateUtil.yyyyMMdd_EN),
                DateUtil.getCurDateTime(DateUtil.yyyyMMddHHmmss_EN)+ CommonUtil.ranStr(8),
                true,
                CommonUtil.isEqual(request.getParameter("resize"), "1")
        );
    }

    public static TbImageSams kindEditorUploadOfName(
            String suffix,
            DictionaryCache dictionaryCache,
            ViyBasicCache viyBasicCache,
            MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response, String dirName, String path, String saveFileName,
            boolean pushPic,
            boolean resize)throws Exception{
        boolean isFile = !CommonUtil.isEqual(dirName, "image");
        if(isFile){
            Integer maxSize = Integer.parseInt(dictionaryCache.getValue(DictionaryKey.Keys.ATTACHMENT_UPLOAD_SIZE_LIMIT.getValue()))*1024;
            if(imgFile.getSize() >= maxSize){
                throw new CustomerException("附件不能大于"+maxSize/1024+"k", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
            }
        }else{
            Integer maxSize = Integer.parseInt(dictionaryCache.getValue(DictionaryKey.Keys.IMAGE_UPLOAD_SIZE_LIMIT.getValue()))*1024;
            if(imgFile.getSize() >= maxSize){
                throw new CustomerException("图片不能大于"+maxSize/1024+"k", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
            }
        }
        // 上传路径
        String savePath=dictionaryCache.getValue(DictionaryKey.Keys.FILE_UPLOAD_ADDRESS.getValue());
        if(StringUtil.isEmpty(savePath)){
            throw new CustomerException("上传图片路径不存在", CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION);
        }
        //文件保存目录路径
        savePath += "/fs/uf/";
        //文件保存目录URL
        String port = ":" + request.getServerPort();
        if(CommonUtil.isEqual(port, ":80")){
            port = "";
        }
        String saveUrl = dictionaryCache.getValue(DictionaryKey.Keys.FILE_SERVER_DOMAIN_NAME.getValue()) + "/fs/uf/";
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap <>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf,swf,flv,mp3,wav,mid,avi,mpg,mp4,rm");
        dirName = !CommonUtil.isEqual(dirName, "image") ? "file" : "image";
        if(!extMap.containsKey(dirName)){
            throw new CustomerException("目录名不正确", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
        }
        int sysType = SysEnumContainer.SysType.PICTORIAL_INFORMATION.getValue();
        if(!CommonUtil.isEqual(dirName, "image")){
            sysType = SysEnumContainer.SysType.FILE_CLASS.getValue();
        }
        // 获得文件名：
        String realFileName = imgFile.getOriginalFilename();
        String extName=realFileName.substring(realFileName.lastIndexOf(".")+1);
        if(!Arrays.asList(extMap.get(dirName).split(",")).contains(extName)){
            throw new CustomerException("上传文件扩展名["+extName+"]是不允许的扩展名，\n只允许" + extMap.get(dirName) + "格式", CommonEnumContainer.ResultStatus.THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS);
        }
        //最大文件大小

        //检查目录
        File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            uploadDir.mkdirs();
        }
        //检查目录写权限
        if(!uploadDir.canWrite()){
            throw new CustomerException("上传目录没有写权限", CommonEnumContainer.ResultStatus.WITHOUT_PERMISSION);
        }
        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        savePath += path + "/";
        saveUrl += path + "/";

        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        String newRealFileName = saveFileName +"." + extName;//保存在数据库中的图
        File uploadFile = new File(savePath + newRealFileName);
        Path _path = Paths.get(savePath + newRealFileName);
        String fileType = Files.probeContentType(_path);
        //实际检查文件类型是否正确开始************************************

        //实际检查文件类型是否正确完毕************************************
        FileCopyUtils.copy(imgFile.getBytes(), uploadFile);

        //压缩图片：只有type != null的情况下
        String limitName = newRealFileName;//小:默认不压缩
        String cName = newRealFileName;//中:默认不压缩
        String bName = newRealFileName;//大:默认不压缩
        //String dF = originalName + "_detail" + extName;//详细页图
        //String aF = originalName + "_album" + extName;//相册图
        //String lF = originalName + "_list" + extName;//列表图

        if(resize){
            //压缩图片
            limitName = saveFileName+"_lmt."+extName;//小
            cName = saveFileName+"_cnt."+extName;//中
            bName = saveFileName+"_big."+extName;//大
            ImageUtils.resize(savePath + newRealFileName, savePath+limitName, 108, 108);
            ImageUtils.resize(savePath + newRealFileName, savePath+cName, 200, 200);
            ImageUtils.resize(savePath + newRealFileName, savePath+bName, 400, 400);
            //PC
            ImageUtils.resize(savePath + newRealFileName, savePath+saveFileName+"_pc."+extName, 230, 520);
            //mobile
            ImageUtils.resize(savePath + newRealFileName, savePath+saveFileName+"_mobile."+extName, 150, 400);
        }
        TbImageSams newSam=new TbImageSams();

        //小图地址
        newSam.setLmtSrc(saveUrl+limitName);
        newSam.setBigSrc(saveUrl + bName);
        newSam.setCntSrc(saveUrl + cName);
        newSam.setType(sysType);
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



    /**
     * 获取上传文件的全名
     * @param dictionaryCache
     * @param req
     * @return
     */
    public static String getSaveFileName(DictionaryCache dictionaryCache, TqCheckFile req){
        String savePath=dictionaryCache.getValue(DictionaryKey.Keys.FILE_UPLOAD_ADDRESS.getValue()) + "/fs/uf/";
        savePath += req.getSavePath() + "/";
        savePath += req.getServiceType() + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return savePath + req.getUserId() + "-"+ req.getFileMd5() + "." + req.getExt();
    }

    /**
     * 获取上传文件的临时名称
     * @param dictionaryCache
     * @param req
     * @return
     */
    public static File getSaveTempPath(DictionaryCache dictionaryCache, TqCheckFile req){
        String savePath=dictionaryCache.getValue(DictionaryKey.Keys.FILE_UPLOAD_ADDRESS.getValue()) + "/fs/uf/";
        savePath += req.getSavePath() + "/";
        savePath += req.getServiceType() + "/";
        savePath += req.getUserId() + "-"+ req.getFileMd5() + "-tmp/";
        return new File(savePath);
    }

    /**
     * 获取上传文件的访问全路径
     * @param dictionaryCache
     * @param req
     * @return
     */
    public static String getSaveFileUrl(DictionaryCache dictionaryCache, TqCheckFile req){
        String saveUrl = dictionaryCache.getValue(DictionaryKey.Keys.FILE_SERVER_DOMAIN_NAME.getValue()) + "/fs/uf/";
        saveUrl += req.getSavePath() + "/";
        saveUrl += req.getServiceType() + "/";
        return saveUrl + req.getUserId() + "-"+ req.getFileMd5() + "." + req.getExt();
    }

    /**
     * 检查某文件是否已上传
     * 上传原理，将上传成功的文件放在type路径下，名称为 (userId-fileMd5).ext。
     * 假如用户已经上传，则返回该文件；假如用户没有上传，则创建一个（userId-fileMd5-tmp）文件夹，里面放有分片信息，等分片全部上传完毕，合并文件
     * 并且删掉临时文件夹
     * @param req 路径+文件md5值
     * @return 已经上传返回 url地址，否则返回 null
     */
    public static TrCheckFile exitsFile(DictionaryCache dictionaryCache, TqCheckFile req){
        TrCheckFile resp = new TrCheckFile();
        File f = new File(getSaveFileName(dictionaryCache,req));
        resp.setExists(f.exists());
        resp.setFileUrl(getSaveFileUrl(dictionaryCache,req));
        if(!f.exists()){
            //查询临时目录下
            List hadChunks = new ArrayList();
            File tmpPath = getSaveTempPath(dictionaryCache,req);
            if(tmpPath.exists()){
                hadChunks.addAll(Arrays.asList(Objects.requireNonNull(tmpPath.list())));
            }
            resp.setHadChunks(hadChunks);
        }
        return resp;
    }


    public static TrCheckFile shareUpload(DictionaryCache dictionaryCache, TqUploadShareFile req)throws IOException {

        //如果已经上传完毕，直接返回地址
        TrCheckFile resp = exitsFile(dictionaryCache,req);
        if(resp.isExists()){
            return resp;
        }
        //如果分片为空，直接上传
        if(null == req.getChunk()){
            FileCopyUtils.copy(req.getFile().getBytes(), new File(getSaveFileName(dictionaryCache,req)));
            resp.setExists(true);
            return resp;
        }
        File tmpPath = getSaveTempPath(dictionaryCache,req);
        if (!tmpPath.exists()) {
            tmpPath.mkdirs();
        }
        //如果分片已经存在，则直接返回已经存在的信息
        File tf = new File(tmpPath.getPath() + "/" + req.getChunk());
        if(tf.exists()){
            return resp;
        }
        FileCopyUtils.copy(req.getFile().getBytes(), tf);
        String[] shareFiles = tmpPath.list();
        if(shareFiles.length == req.getChunks()){
            //合并
            if(!mergeFiles(tmpPath,getSaveFileName(dictionaryCache,req))){
                throw new CustomerException("文件合并失败！", CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION);
            }
            resp.setExists(true);
            resp.setHadChunks(Arrays.asList(Objects.requireNonNull(shareFiles)));
        }


        return resp;
    }


    private static boolean mergeFiles(File tmpFile, String resultPath) {
        File[] files = tmpFile.listFiles();
        if (files == null || files.length < 1 || StringUtil.isEmpty(resultPath)) {
            return false;
        }
        if (files.length == 1) {
            return files[0].renameTo(new File(resultPath));
        }

        File resultFile = new File(resultPath);
        //排序
        Arrays.sort(files, Comparator.comparing(o -> Integer.valueOf(o.getName())));
        try {
            FileChannel resultFileChannel = new FileOutputStream(resultFile, true).getChannel();
            for (int i = 0; i < files.length; i ++) {
                FileChannel blk = new FileInputStream(files[i]).getChannel();
                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
                blk.close();
            }
            resultFileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        for(File f:files){
            f.delete();
        }
        tmpFile.delete();

        return true;
    }


}

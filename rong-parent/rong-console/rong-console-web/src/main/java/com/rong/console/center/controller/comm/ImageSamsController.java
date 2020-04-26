package com.rong.console.center.controller.comm;

import com.rong.cache.base.ViyBasicCache;
import com.rong.cache.service.DictionaryCache;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.consts.DictionaryKey;
import com.rong.common.module.QueryInfo;
import com.rong.common.module.Result;
import com.rong.common.module.TvPageList;
import com.rong.common.util.JSONUtil;
import com.rong.console.center.config.FastDFSClientWrapper;
import com.rong.console.center.controller.BaseController;
import com.rong.console.center.module.request.TqCheckFile;
import com.rong.console.center.module.request.TqUploadShareFile;
import com.rong.console.center.module.response.TrCheckFile;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.UploadFileUtil;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.consts.SysEnumContainer;
import com.rong.sys.module.entity.TbImageSams;
import com.rong.sys.module.query.TsImageSams;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.module.request.TqImageSams;
import com.rong.sys.module.view.TvImageSams;
import com.rong.sys.service.ImageSamsService;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.wrapper.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 图片管理
 * @author lether
 *
 */
@Controller
@RequestMapping(RightConst.COMM_IMAGESAMES_PATH)
public class ImageSamsController extends BaseController<TbImageSams, TqImageSams, TvImageSams, ImageSamsService> {

    private final LabelService labelService;
    private final DictionaryCache dictionaryCache;
    private final FastDFSClientWrapper fastDFSClientWrapper;
    private final ViyBasicCache viyBasicCache;
    @Autowired
    public ImageSamsController(LabelService labelService, DictionaryCache dictionaryCache, FastDFSClientWrapper fastDFSClientWrapper, ViyBasicCache viyBasicCache) {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_IMAGESAMES_PATH)
                .build(), TsImageSams.Fields.id);
        this.labelService = labelService;
        this.dictionaryCache = dictionaryCache;
        this.fastDFSClientWrapper = fastDFSClientWrapper;
        this.viyBasicCache = viyBasicCache;
    }
    /**
     * 获得图片列表
     * @param request
     * @param queryInfo
     * @return
     */
    @RequestMapping("simlist")
    @ResponseBody
    public Result<TvPageList<TvImageSams>> list(QueryInfo queryInfo, HttpServletRequest request, HttpServletResponse response)throws Exception{
        return super.dataGrid(request,response,queryInfo);
    }
    /**
     * 列表页面
     * @param request
     * @param multi
     * @return
     */
    @GetMapping("img")
    public String selectimg(HttpServletRequest request,Integer multi)throws Exception{
        List list= labelService.selectList(new QueryWrapper()
                .select(TsLabel.Fields.id, TsLabel.Fields.name)
                .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.IMAGE_LABEL.getValue())
                .eq(TsLabel.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                .eq(TsLabel.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        Integer maxSize = Integer.parseInt(dictionaryCache.getValue(DictionaryKey.Keys.IMAGE_UPLOAD_SIZE_LIMIT.getValue()));
        request.setAttribute("maxSize", maxSize);
        request.setAttribute("multi", multi);
        request.setAttribute("imgLabelList", JSONUtil.toJSONString(list));
        return viewModel.getBasePath()+"img";
    }
    @GetMapping("video")
    public String video(HttpServletRequest request)throws Exception{
        return viewModel.getBasePath()+"video";
    }
    /**
     * 列表页面
     * @param request
     * @param request
     * @param multi
     * @return
     */
    @GetMapping("file")
    public String selectFile(HttpServletRequest request,Integer multi)throws Exception{

        List list= labelService.selectList(new QueryWrapper()
                .select(TsLabel.Fields.id, TsLabel.Fields.name)
                .eq(TsLabel.Fields.type, SysEnumContainer.LabelType.FILE_CLASS.getValue())
                .eq(TsLabel.Fields.state, CommonEnumContainer.State.NORMAL.getValue())
                .eq(TsLabel.Fields.deltag, CommonEnumContainer.Deltag.NORMAL.getValue())
        );
        request.setAttribute("labelList", JSONUtil.toJSONString(list));
        Integer maxSize = Integer.parseInt(dictionaryCache.getValue(DictionaryKey.Keys.ATTACHMENT_UPLOAD_SIZE_LIMIT.getValue()));
        request.setAttribute("maxSize", maxSize);
        request.setAttribute("multi", multi);
        return viewModel.getBasePath()+"file";
    }
    @PostMapping(value="upfile")
    @ResponseBody
    public Result kindEditorUpload(@RequestParam(value = "imgFile", required = false) MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response)throws Exception{
        return Result.success(UploadFileUtil.kindEditorUpload("",dictionaryCache,viyBasicCache,imgFile,request,response,request.getParameter("dir")));
        //return fastDFSClientWrapper.uploadFile("",imgFile,request,true);
    }


    /**
     * 上传大文件[分片上传]
     */
    @ApiOperation(value = "上传视频等大文件[分片上传]")
    @PostMapping("upload")
    @ResponseBody
    public Result<TrCheckFile> uploadLargeFile(TqUploadShareFile req)throws IOException {
        req.setLoginUserId(0L);
        return Result.success(UploadFileUtil.shareUpload(dictionaryCache,req));
    }

    /**
     * 检查文件是否已经存在：根据传过来的MD5 和路径查询。
     */
    @ApiOperation(value = "检查文件是否已经存在：根据传过来的MD5 和路径查询")
    @GetMapping("checkFile")
    @ResponseBody
    public Result<TrCheckFile> checkFile(TqCheckFile req){
        req.setLoginUserId(0L);
        return Result.success(UploadFileUtil.exitsFile(dictionaryCache,req));
    }


}

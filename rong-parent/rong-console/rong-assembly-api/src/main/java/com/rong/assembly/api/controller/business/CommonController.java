package com.rong.assembly.api.controller.business;

import com.rong.assembly.api.module.request.TqAdList;
import com.rong.assembly.api.module.request.TqAppVersionInfo;
import com.rong.assembly.api.module.request.TqGetBannerList;
import com.rong.assembly.api.module.request.TqGetLabelList;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.exception.NoExistsException;
import com.rong.common.module.Result;
import com.rong.common.util.WrapperFactory;
import com.rong.sys.module.entity.TbDictionary;
import com.rong.sys.module.query.TsBanner;
import com.rong.sys.module.query.TsDictionary;
import com.rong.sys.module.query.TsLabel;
import com.rong.sys.module.view.TvAdvertise;
import com.rong.sys.module.view.TvBanner;
import com.rong.sys.module.view.TvLabel;
import com.rong.sys.service.AdvertiseService;
import com.rong.sys.service.BannerService;
import com.rong.sys.service.DictionaryService;
import com.rong.sys.service.LabelService;
import com.vitily.mybatis.core.enums.Order;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.core.wrapper.sort.OrderBy;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.SelectAlias;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "资源中心")
@RequestMapping("recourse")
@RestController
public class CommonController {
    @Autowired
    BannerService bannerService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private AdvertiseService advertiseService;
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * banner列表
     */
    @GetMapping("banner/list")
    @ApiOperation(value = "banner列表")
    public Result<List<TvBanner>> bannerList(TqGetBannerList req){
        return Result.success(
                bannerService.selectViewList(new MultiTableQueryWrapper()
                        .eq(CompareAlias.valueOf(TsBanner.Fields.pageType,"e"),req.getPageType())
                        .eq(CompareAlias.valueOf(TsBanner.Fields.state,"e"), CommonEnumContainer.State.NORMAL.getValue())
                        .eq(CompareAlias.valueOf(TsBanner.Fields.deltag,"e"), CommonEnumContainer.Deltag.NORMAL.getValue())
                        .orderBy(OrderBy.valueOf(Order.ASC,
                                SelectAlias.valueOf(TsBanner.Fields.sort,"e")
                        ))
                )
        );
    }

    /**
     * 标签列表
     */
    @GetMapping("label/list")
    @ApiOperation(value = "标签列表")
    public Result<List<TvLabel>> labelList(TqGetLabelList req){
        return Result.success(
                labelService.selectViewList(WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf(TsLabel.Fields.type,"e"),req.getLabelType())
                        .eq(CompareAlias.valueOf(TsLabel.Fields.state,"e"), CommonEnumContainer.State.NORMAL.getValue())
                        .eq(CompareAlias.valueOf(TsLabel.Fields.deltag,"e"), CommonEnumContainer.Deltag.NORMAL.getValue())
                        .orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf(TsBanner.Fields.sort,"e")))
                        .page(req.getPageInfo())
                )
        );
    }

    /**
     * 广告列表
     */
    @GetMapping("ad/list")
    @ApiOperation(value = "广告列表")
    public Result<List<TvAdvertise>> adList(TqAdList req){
        MultiTableQueryWrapper queryWrapper = WrapperFactory.multiQueryWrapper();
        if(null != req.getType()){
            queryWrapper.eq(CompareAlias.valueOf("e.type"),req.getType());
        }
        if(null != req.getPosition()){
            queryWrapper.eq(CompareAlias.valueOf("e.position"),req.getPosition());
        }

        return Result.success(
                advertiseService.selectViewList(queryWrapper
                        .eq(CompareAlias.valueOf(TsLabel.Fields.state,"e"), CommonEnumContainer.State.NORMAL.getValue())
                        .eq(CompareAlias.valueOf(TsLabel.Fields.deltag,"e"), CommonEnumContainer.Deltag.NORMAL.getValue())
                        .orderBy(OrderBy.valueOf(Order.ASC,SelectAlias.valueOf(TsBanner.Fields.sort,"e")))
                        .page(req.getPageInfo())
                )
        );
    }

    /**
     * app 版本信息
     */
    @GetMapping("app/info")
    @ApiOperation(value = "app 版本信息")
    public Result<TbDictionary> appInfo(TqAppVersionInfo req){
        TbDictionary dictionary = dictionaryService.selectOne(
          WrapperFactory.queryWrapper().eq(TsDictionary.Fields.key,req.getType() + "_APP_INFO")
        );
        if(dictionary == null){
            throw  new NoExistsException("找不到版本号");
        }
        return Result.success(dictionary);
    }

}
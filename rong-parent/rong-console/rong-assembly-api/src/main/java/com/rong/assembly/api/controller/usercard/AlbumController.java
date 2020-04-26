package com.rong.assembly.api.controller.usercard;


import com.rong.assembly.api.module.request.usercard.TqMySimpleList;
import com.rong.assembly.api.module.request.usercard.TqSimpleRemoveList;
import com.rong.assembly.api.module.request.usercard.TqUpdateAlbum;
import com.rong.common.module.Result;
import com.rong.common.util.WrapperFactory;
import com.rong.usercard.module.entity.TbUserCardAlbum;
import com.rong.usercard.module.query.TsUserCardAlbum;
import com.rong.usercard.module.request.TqUserCardAlbum;
import com.rong.usercard.module.view.TvUserCardAlbum;
import com.rong.usercard.service.UserCardAlbumService;
import com.vitily.mybatis.core.wrapper.PageList;
import com.vitily.mybatis.core.wrapper.query.MultiTableQueryWrapper;
import com.vitily.mybatis.util.CompareAlias;
import com.vitily.mybatis.util.Elements;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 相册
 */
@Api(tags = "相册")
@RestController
@RequestMapping("usercard/album")
public class AlbumController {
    @Autowired
    private UserCardAlbumService userCardAlbumService;

    /**
     * 相册列表
     * @param req
     * @return
     */
    @ApiOperation(value = "相册列表")
    @GetMapping("list")
    public Result<PageList<TvUserCardAlbum>> list(TqMySimpleList req){
        MultiTableQueryWrapper queryWrapper =
                WrapperFactory.multiQueryWrapper()
                        .eq(CompareAlias.valueOf("e.userId"),req.getUserId())
                        .eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        if(null != req.getDeltag()){
            queryWrapper.eq(CompareAlias.valueOf("e.deltag"),req.getDeltag());
        }
        return Result.success(userCardAlbumService.selectPageListV(queryWrapper));
    }

    /**
     * 添加相册
     * @param req
     * @return
     */
    @ApiOperation(value = "添加相册")
    @PostMapping("add")
    public Result<Boolean> add(@RequestBody TqUpdateAlbum req){
        TbUserCardAlbum tbUserCardAlbum = new TbUserCardAlbum()
                .setUserId(req.getUserId())
                .setPicUrl(req.getPicUrl())
                .setPicName(req.getPicName())
                .setRemark(req.getRemark());
        userCardAlbumService.insert(new TqUserCardAlbum().setEntity(tbUserCardAlbum));
        return Result.success(Boolean.TRUE);
    }

    /**
     * 删除相册
     * @param req
     * @return
     */
    @ApiOperation(value = "删除相册")
    @PostMapping("remove")
    public Result<Boolean> remove(@RequestBody TqSimpleRemoveList req){
        userCardAlbumService.updateSelectItem(
                WrapperFactory.updateWrapper()
                        .update(
                                Elements.valueOf(TsUserCardAlbum.Fields.deltag,true)
                                ,
                                Elements.valueOf(TsUserCardAlbum.Fields.updateDate,new Date())
                        )
                        .in(TsUserCardAlbum.Fields.id,req.getIds())
        );
        return Result.success(Boolean.TRUE);
    }
}
